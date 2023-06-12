package FileUploadDownload;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
	response.setContentType("text/html; charset=UTF-8");
	String filename = request.getParameter("path");
	  System.out.println(filename);
	//String filename=new String(request.getParameter("path").getBytes("ISO-8859-1"),"UTF-8");
	// GET请求中，参数中包含中文需要自己动手来转换。
	// 当然如果你使用了“全局编码过滤器”，那么这里就不用处理了
	//filename = new String(filename.getBytes("ISO-8859-1"), "UTF-8");
	// System.out.print(filename);
	String filepath = this.getServletContext().getRealPath("/WEB-INF/UploadFiles/" + filename);
	File file = new File(filepath);
	if(!file.exists()) {
		response.getWriter().print("您要下载的文件不存在！");
		return;
	}
	// 所有浏览器都会使用本地编码，即中文操作系统使用UTF-8
	// 浏览器收到这个文件名后，会使用iso-8859-1来解码
	filename = new String(filename.getBytes("UTF-8"), "ISO-8859-1");
	response.addHeader("content-disposition", "attachment;filename=" + filename);
	IOUtils.copy(new FileInputStream(file), response.getOutputStream());
	}
}
