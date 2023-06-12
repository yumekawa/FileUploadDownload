package FileUploadDownload;
 
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
 
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.*;
import java.util.List;
import java.util.UUID;
 
public class UploadServlet extends HttpServlet {
 
  private static final long serialVersionUID = 1L;
 
  @Override

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
    try {
//      ����ContentType�ֶ�
    	response.setContentType("text/html;charset=utf8");
//     ����DiskFileItemFactory�������󣬿��Խ�������Ϣʵ���е�ÿһ���ļ���װ�ɵ�����FielItem����
      DiskFileItemFactory factory = new DiskFileItemFactory();
 
//     �����ļ�����Ŀ¼��������ļ����������´���һ��
      File f = new File("/Users/Desktop/my");
      if (!f.exists()) {
        f.mkdir();
      }
 
//      �����ļ��Ļ���·��
      factory.setRepository(f);
//      ����һ��ServletFileUpload����
      ServletFileUpload fileUpload = new ServletFileUpload(factory);
//      �����ַ�����
      fileUpload.setHeaderEncoding("utf-8");
 
//      ����request���õ��ϴ��ļ���FileItem����
      List<FileItem> fileItems = fileUpload.parseRequest(request);
//      ��ȡ�ַ���
      PrintWriter writer = response.getWriter();
 
//      ��������fileItems�����List����
      for (FileItem fileItem : fileItems) {
//        �жϵ�ǰ��FileItem�����װ��������"��ͨ�ı����ֶ�"
        if (fileItem.isFormField()) {
//          ����ϴ��ı��ֶε�name����ֵ
          String name = fileItem.getFieldName();
          if (name.equals("name")) {
//            ���FileItem�����б������������Ϊ�գ��򷵻�һ���ַ�������ͨ��print���
            if (!fileItem.getString().equals("")) {
              String value = fileItem.getString("utf-8");
              writer.print("�ϴ��ߣ�" + value + "<br/>");
            }
          }
        } else {//�жϵ�ǰ��FileItem�����װ��������"�ļ����ֶ�"
//          ����ϴ��ļ����ļ���
          String filename = fileItem.getName();
          if (fileItem != null && !fileItem.equals("")) {
            writer.print("�ϴ����ļ�������" + filename + "<br/>");
//            ��ȡ���ļ���
            filename = filename.substring(filename.lastIndexOf("\\") + 1);
//            �����ļ���һ��Ψһ��id
            filename = UUID.randomUUID().toString() + "_uuid_name_" + filename;
 
//            �ڷ������д���ͬ���ļ�
            String webPath = "./WEB-INF/UploadFiles/";
//            �����������ļ���·�����ļ�����ϳ������ķ�������·��
            String filePath = getServletContext().getRealPath(webPath + filename);
 
//            �����ļ�
            File file = new File(filePath);
            file.getParentFile().mkdir();
            file.createNewFile();
 
//            ����ϴ��ļ���
            InputStream in = fileItem.getInputStream();
//            �򿪷������˵��ϴ��ļ�
            FileOutputStream fileOutputStream = new FileOutputStream(file);
 
//            ���ĶԿ�  ����Ϊ1024*1024*1024
            byte buffer[] = new byte[1073741824];
            int len;
            while ((len = in.read(buffer)) > 0) {
              fileOutputStream.write(buffer, 0, len);
              in.close();
              fileOutputStream.close();
              fileItem.delete();
              writer.print("�ϴ��ļ��ɹ�");
 
            }
          }
        }
      }
//�ϴ�������ת������ҳ��
      request.getRequestDispatcher("ReadFolder").forward(request,response);
 
    } catch (FileUploadException e) {
      e.printStackTrace();
    }
  }
 
 
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }
 
}