package LLD.LoadBalancer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


interface LoadBalancerAlgorithm{
	Server balanceLoad(List<Server> servers);
}

class RoundRobin implements LoadBalancerAlgorithm{
	
	private int currentServer;
	
	public RoundRobin(){
		this.currentServer = 0;
	}
	
	@Override 
	public Server balanceLoad( List<Server> servers ) {
		int n = servers.size();
		this.currentServer = this.currentServer%n;
		return servers.get( this.currentServer++ );
	}
}

class LeastConnection implements LoadBalancerAlgorithm{
	@Override 
	public Server balanceLoad( List<Server> servers ) {
		return servers.stream()
				.min( Comparator.comparingInt( srv -> srv.numOfRequestsServing ))
				.orElseThrow();
	}
}

class Server{
	String id;
	String ipAddress;
	int numOfRequestsServing;
	int capacity;
	
	public Server(String id, String ipAddress, int capacity){
		this.id = id.toUpperCase();
		this.ipAddress = ipAddress;
		this.capacity = capacity;
		this.numOfRequestsServing = 0;
	}

	public boolean acceptRequest(Request req) {
		if( req != null ) {
			this.numOfRequestsServing++;
			return true;	
		}
		return false;
	}
	
	public boolean completeRequest() {
		// process and complete requests
		this.numOfRequestsServing--;
		return true;
	}
	
	
	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public int getNumOfRequestsServing() {
		return numOfRequestsServing;
	}

	public String getId() {
		return id;
	}

	public int getCapacity() {
		return capacity;
	}
		
}

class Request{
	String id;
	Map<String, String> params;
	
	public Request( String id, Map<String, String> params ) {
		this.id = id.toUpperCase();
		this.params = params;
	}
	
	public String getId() {
		return this.id;
	}
}


class LoadBalancer{
	
	private List<Server> servers;
	LoadBalancerAlgorithm algorithm;
	
	public LoadBalancer( LoadBalancerAlgorithm algorithm ) {
		this.algorithm = algorithm;
		this.servers = new ArrayList<>();
	}
	
	public void addServer( Server server ) {
		this.servers.add( server );
	}
	
	public Server removeServer(String id ) {
		for( Server srv: this.servers ) {
			if( srv.getId().equals( id.toUpperCase() ) ) {
				this.servers.remove( srv );
				return srv;
			}
		}
		return null;
	}
	
	public Server getServer(String id) {
		for( Server srv: this.servers ) {
			if( srv.getId().equals( id.toUpperCase() ) ) {
				return srv;
			}
		}
		return null;
	}
	
	public void setAlgorithm( LoadBalancerAlgorithm algorithm ) {
		this.algorithm = algorithm;
	}
	
	public LoadBalancerAlgorithm getAlgorithm( ) {
		return this.algorithm;
	}
	
	public Server forwardRequest(Request req) {
		
		if( this.servers.size() <= 0 ) {
			System.out.println("The load balancer does not have any active server.");
			return null;
		}
		
		Server destination = this.algorithm.balanceLoad( this.servers );
		
		if( destination == null ) {
			System.out.println("Server not found!");
			return null;
		}
		
		if( destination.acceptRequest(req) ) {
			System.out.println( "The request "+ req.getId() + " is forwarded to server with IP "+ destination.getIpAddress() );			
			return destination;
		}
		System.out.println( "The request "+ req.getId() + " is denied by Server with IP "+ destination.getIpAddress() );			
		return null;
	}
	
}

public class Demo {

	public static void main(String[] args) {
		
		// Request
		String reqId = "098765oiuyh";
		Map<String, String> reqParams = new HashMap<>();
		reqParams.put("Name", "Groot");
		Request req = new Request(reqId, reqParams);
		// Algorithm
		LoadBalancerAlgorithm roundRobin = new RoundRobin();
		LoadBalancerAlgorithm leastConnection = new LeastConnection();
		// Servers
		Server srv1 = new Server( "987654dfvbnm", "120.000.11.11", 5 );
		Server srv2 = new Server( "987655dfvbnm", "120.000.11.12", 5 );
		Server srv3 = new Server( "987656dfvbnm", "120.000.11.13", 5 );
		Server srv4 = new Server( "987657dfvbnm", "120.000.11.14", 5 );
		Server srv5 = new Server( "987658dfvbnm", "120.000.11.15", 5 );
		
		List<Server> servers = Arrays.asList( srv1, srv2, srv3, srv4, srv5 ); 
		
		// Load Balancer
		LoadBalancer loadBalancer = new LoadBalancer(roundRobin);
		
		// Add Servers to LB
		servers.forEach( srv -> loadBalancer.addServer(srv) );
		
		// Testing Round Robin Algorithm
		System.out.println( "Testing Round Robin Algorithm" );
		
		loadBalancer.forwardRequest(req);
		loadBalancer.forwardRequest(req);
		loadBalancer.forwardRequest(req);
		loadBalancer.forwardRequest(req);
		loadBalancer.forwardRequest(req);
		loadBalancer.forwardRequest(req);
		
		// Testing Least Connection Algorithm
		System.out.println( "Testing Least Connection Algorithm" );
		
		loadBalancer.setAlgorithm(leastConnection);
		
		Server chosenServ1 = loadBalancer.forwardRequest(req);
		Server chosenServ2 = loadBalancer.forwardRequest(req);
		Server chosenServ3 = loadBalancer.forwardRequest(req);
		Server chosenServ4 = loadBalancer.forwardRequest(req);
		Server chosenServ5 = loadBalancer.forwardRequest(req);
		Server chosenServ6 = loadBalancer.forwardRequest(req);
		chosenServ1.completeRequest();
		chosenServ1.completeRequest();
		Server chosenServ7 = loadBalancer.forwardRequest(req);
		Server chosenServ8 = loadBalancer.forwardRequest(req);
	}

}
