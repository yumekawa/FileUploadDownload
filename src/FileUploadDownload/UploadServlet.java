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
//      设置ContentType字段
    	response.setContentType("text/html;charset=utf8");
//     创建DiskFileItemFactory工厂对象，可以将请求消息实体中的每一个文件封装成单独的FielItem对象
      DiskFileItemFactory factory = new DiskFileItemFactory();
 
//     设置文件缓存目录，如果该文件不存在则新创建一个
      File f = new File("/Users/Desktop/my");
      if (!f.exists()) {
        f.mkdir();
      }
 
//      设置文件的缓存路径
      factory.setRepository(f);
//      创建一个ServletFileUpload对象
      ServletFileUpload fileUpload = new ServletFileUpload(factory);
//      设置字符编码
      fileUpload.setHeaderEncoding("utf-8");
 
//      解析request，得到上传文件的FileItem对象
      List<FileItem> fileItems = fileUpload.parseRequest(request);
//      获取字符流
      PrintWriter writer = response.getWriter();
 
//      遍历对象fileItems对象的List集合
      for (FileItem fileItem : fileItems) {
//        判断当前的FileItem对象封装的数据是"普通文本表单字段"
        if (fileItem.isFormField()) {
//          获得上传的表单字段的name属性值
          String name = fileItem.getFieldName();
          if (name.equals("name")) {
//            如果FileItem对象中保存的数据流不为空，则返回一个字符串，并通过print输出
            if (!fileItem.getString().equals("")) {
              String value = fileItem.getString("utf-8");
              writer.print("上传者：" + value + "<br/>");
            }
          }
        } else {//判断当前的FileItem对象封装的数据是"文件表单字段"
//          获得上传文件的文件名
          String filename = fileItem.getName();
          if (fileItem != null && !fileItem.equals("")) {
            writer.print("上传的文件名称是" + filename + "<br/>");
//            截取出文件名
            filename = filename.substring(filename.lastIndexOf("\\") + 1);
//            给定文件名一个唯一的id
            filename = UUID.randomUUID().toString() + "_uuid_name_" + filename;
 
//            在服务器中创建同名文件
            String webPath = "./WEB-INF/UploadFiles/";
//            将服务器中文件夹路径与文件名组合成完整的服务器端路径
            String filePath = getServletContext().getRealPath(webPath + filename);
 
//            创建文件
            File file = new File(filePath);
            file.getParentFile().mkdir();
            file.createNewFile();
 
//            获得上传文件流
            InputStream in = fileItem.getInputStream();
//            打开服务器端的上传文件
            FileOutputStream fileOutputStream = new FileOutputStream(file);
 
//            流的对拷  限制为1024*1024*1024
            byte buffer[] = new byte[1073741824];
            int len;
            while ((len = in.read(buffer)) > 0) {
              fileOutputStream.write(buffer, 0, len);
              in.close();
              fileOutputStream.close();
              fileItem.delete();
              writer.print("上传文件成功");
 
            }
          }
        }
      }
//上传结束跳转到下载页面
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