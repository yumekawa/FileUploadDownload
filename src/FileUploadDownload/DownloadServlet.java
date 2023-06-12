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
	// GET�����У������а���������Ҫ�Լ�������ת����
	// ��Ȼ�����ʹ���ˡ�ȫ�ֱ��������������ô����Ͳ��ô�����
	//filename = new String(filename.getBytes("ISO-8859-1"), "UTF-8");
	// System.out.print(filename);
	String filepath = this.getServletContext().getRealPath("/WEB-INF/UploadFiles/" + filename);
	File file = new File(filepath);
	if(!file.exists()) {
		response.getWriter().print("��Ҫ���ص��ļ������ڣ�");
		return;
	}
	// �������������ʹ�ñ��ر��룬�����Ĳ���ϵͳʹ��UTF-8
	// ������յ�����ļ����󣬻�ʹ��iso-8859-1������
	filename = new String(filename.getBytes("UTF-8"), "ISO-8859-1");
	response.addHeader("content-disposition", "attachment;filename=" + filename);
	IOUtils.copy(new FileInputStream(file), response.getOutputStream());
	}
}
