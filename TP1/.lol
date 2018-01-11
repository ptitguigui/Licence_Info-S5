pas besoin de port pour emission

emission: ip + (port)
reception: ip + port

datagram socket
datagram packet

chat

class TchatThread extends Thread {
	
	TchatThread(params) {
		...
	}
	
	void run() {
		// equivalent au run
		// code
	}
}

tc = new Tchat(...);
NE PAS faire tc.run
mais faire tc.start
