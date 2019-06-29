package MyServer;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
 
public class HttpServer {
  private static final Integer port = 80;//HTTP默认端口80
 
  public static void main(String[] args) {
    ServerSocket serverSocket;
 
    try {
      //建立服务器Socket,监听客户端请求
      serverSocket = new ServerSocket(port);
      System.out.println("Server is running on port:"+serverSocket.getLocalPort());
      //死循环不间断监听客户端请求
      while(true){
        final Socket socket = serverSocket.accept();
        System.out.println("biuld a new tcp link with client,the cient address:"+ 
            socket.getInetAddress()+":"+socket.getPort());
        //并发处理HTTP客户端请求
        service(socket);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
 
  public static void service(Socket socket)
  {
    new Thread(){
      public void run(){
        InputStream inSocket;
        try {
          //获取HTTP请求头
          inSocket = socket.getInputStream();
          int size = inSocket.available();
          byte[] buffer = new byte[size];
          inSocket.read(buffer);
          String request = new String(buffer);
          System.out.println("ClientBrowser:\n"+request+"\n"
              + "------------------------------------------------------------------");

          ByteArrayInputStream is=new ByteArrayInputStream(request.getBytes());
          BufferedReader br=new BufferedReader(new InputStreamReader(is));
          
          
          String firstLineOfRequest = "";
          String method = "";
          String secondLineOfRequest = "";
          String[] heads;
          String[] split = null;
          String uri = "";
          String contentType ="";
          if(request.length() > 0){
            firstLineOfRequest = request.substring(0,request.indexOf("\r\n"));
            split = firstLineOfRequest.split(" ");
            if(split.length != 3) {
            	contentType = null;
            }
            else if(split[0].equals("GET") || split[0].equals("HEAD")) {

	            heads = firstLineOfRequest.split(" ");
	            uri = heads[1];
	
	            if(split.length != 3) {
	            	contentType = null;
	            }else if(uri.indexOf("html") != -1){
	              contentType = "text/html";
	            }else{
	              contentType = "application/octet-stream";
	            }
            }
            
            else if(split[0].equals("POST")) {

	            heads = firstLineOfRequest.split(" ");
	            uri = heads[1];
	
	            if(split.length != 3) {
	            	contentType = null;
	            }else if(uri.indexOf("html") != -1){
	              contentType = "text/html";
	            }else{
	              contentType = "application/octet-stream";
	            }

            }
            
            
          }
          //将响应头发送给客户端
          if(split != null) {
	          if((contentType.equals("") == false || contentType != null) && split[0].equals("GET")) {
		          OutputStream outSocket = socket.getOutputStream();
		          //通过HTTP请求中的uri读取相应文件发送给客户端
		          if(uri.equals("/") == false && uri.equals("\\") == false) {
			          File file = new File("D:\\Server"+uri);
			          if(file.exists()) {
							BufferedReader reader = new BufferedReader(new FileReader(file));
							StringBuffer sb = new StringBuffer();
							String line = null;
							while ((line = reader.readLine()) != null) {
								sb.append(line).append("\r\n");
							}
							StringBuffer result = new StringBuffer();
							result.append("HTTP/1.1 200 ok \r\n");
							result.append("Content-Language:zh-CN \r\n");
							// charset=UTF-8 解决中文乱码问题
							result.append("Content-Type:text/html;charset=UTF-8 \r\n");
							result.append("Content-Length:" + file.length() + "\r\n");
							result.append("\r\n" + sb.toString());
							outSocket.write(result.toString().getBytes());
							outSocket.flush();
							outSocket.close();
							System.out.print(result.toString());
			          }
			          else {
			  			StringBuffer error = new StringBuffer();
						error.append("HTTP/1.1 404 file not found \r\n");
						error.append("Content-Type:text/html \r\n");
						error.append("Content-Length:20 \r\n").append("\r\n");
						error.append("<h1 >File Not Found..</h1>");
						try {
							outSocket.write(error.toString().getBytes());
							outSocket.flush();
							outSocket.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
			          }
		          }
		          else {
			  			StringBuffer error = new StringBuffer();
						error.append("HTTP/1.1 404 file not found \r\n");
						error.append("Content-Type:text/html \r\n");
						String myString = new String("\r\n" + "<html>\r\n<body>\r\n<h1>File not found</h1>\r\n</body>\r\n</html>\r\n");
						error.append("Content-Length:" + myString.length() + "\r\n");
						error.append(myString);
						outSocket.write(error.toString().getBytes());
						outSocket.flush();
						outSocket.close();
						System.out.print(error.toString());

		          }
	          }
	          else if((contentType.equals("") == false || contentType != null) && split[0].equals("HEAD")) {
		          OutputStream outSocket = socket.getOutputStream();
		          //通过HTTP请求中的uri读取相应文件发送给客户端
		          if(uri.equals("/") == false && uri.equals("\\") == false) {
			          File file = new File("D:\\Server"+uri);
			          if(file.exists()) {
							StringBuffer result = new StringBuffer();
							result.append("HTTP/1.1 200 ok \r\n");
							result.append("Content-Language:zh-CN \r\n");
							// charset=UTF-8 解决中文乱码问题
							result.append("Content-Type:text/html;charset=UTF-8 \r\n");
							result.append("Content-Length:" + file.length() + "\r\n");
							outSocket.write(result.toString().getBytes());
							outSocket.flush();
							outSocket.close();
							System.out.print(result.toString());
			          }
			          else {
			  			StringBuffer error = new StringBuffer();
						error.append("HTTP/1.1 404 file not found \r\n");
						error.append("Content-Type:text/html \r\n");
						error.append("Content-Length:20 \r\n").append("\r\n");
						error.append("<h1 >File Not Found..</h1>");
						try {
							outSocket.write(error.toString().getBytes());
							outSocket.flush();
							outSocket.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
			          }
		          }
		          else {
			  			StringBuffer error = new StringBuffer();
						error.append("HTTP/1.1 404 file not found \r\n");
						error.append("Content-Type:text/html \r\n");
						String myString = new String("\r\n" + "<html>\r\n<body>\r\n<h1>File not found</h1>\r\n</body>\r\n</html>\r\n");
						error.append("Content-Length:" + myString.length() + "\r\n");
						error.append(myString);
						outSocket.write(error.toString().getBytes());
						outSocket.flush();
						outSocket.close();
						System.out.print(error.toString());

		          }
	          }
	          
	          else if((contentType.equals("") == false || contentType != null) && split[0].equals("POST")) {
		          OutputStream outSocket = socket.getOutputStream();
		          //通过HTTP请求中的uri读取相应文件发送给客户端
		          if(uri.equals("/") == false && uri.equals("\\") == false) {

			          File file = new File("D:\\Server"+uri);
			          if(file.exists()) {
							StringBuffer result = new StringBuffer();
							result.append("HTTP/1.1 200 ok \r\n");
							result.append("Content-Language:zh-CN \r\n");
							// charset=UTF-8 解决中文乱码问题
							result.append("Content-Type:text/html;charset=UTF-8 \r\n");
							
							for(int i=0;i<11;i++) {
								br.readLine();
							}
							
							String myString = new String("\r\n" + "<html>\r\n<body>\r\n<h1>Post:" + br.readLine() + "</h1>\r\n</body>\r\n</html>\r\n");
							result.append("Content-Length:" + myString.length() + "\r\n");
							result.append(myString);
							
							outSocket.write(result.toString().getBytes());
							outSocket.flush();
							outSocket.close();
							System.out.print(result.toString());
			          }
			          else {
			  			StringBuffer error = new StringBuffer();
						error.append("HTTP/1.1 404 file not found \r\n");
						error.append("Content-Type:text/html \r\n");
						error.append("Content-Length:20 \r\n").append("\r\n");
						error.append("<h1 >File Not Found..</h1>");
						try {
							outSocket.write(error.toString().getBytes());
							outSocket.flush();
							outSocket.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
			          }
		          }
		          else {
						StringBuffer result = new StringBuffer();
						result.append("HTTP/1.1 200 ok \r\n");
						result.append("Content-Language:zh-CN \r\n");
						// charset=UTF-8 解决中文乱码问题
						result.append("Content-Type:text/html;charset=UTF-8 \r\n");
						
						for(int i=0;i<11;i++) {
							br.readLine();
						}
						
						String myString = new String("\r\n" + "<html>\r\n<body>\r\n<h1>Post:" + br.readLine() + "</h1>\r\n</body>\r\n</html>\r\n");
						result.append("Content-Length:" + myString.length() + "\r\n");
						result.append(myString);
						
						outSocket.write(result.toString().getBytes());
						outSocket.flush();
						outSocket.close();
						System.out.print(result.toString());
		          }
	          }
          }
          
 
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }.start();
  }
 
}