package FileUploadDownload;
	import java.io.FileNotFoundException;
	import java.io.IOException;
	import java.io.File;
	 
	import javax.servlet.ServletException;
	import javax.servlet.ServletRequest;
	import javax.servlet.ServletResponse;
	import javax.servlet.http.HttpServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
	import javax.servlet.http.HttpSession;

	import org.apache.commons.fileupload.FileUploadException;
	import java.util.ArrayList;
	
	public class ReadFolder extends HttpServlet {
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        try {
	            // ִ������
	        	//readfile("C:/Users/***/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/FileUploadDownload/WEB-INF/UploadFiles");
	            // ת��������ҳ��
	            ArrayList<String> filenames = readfile("C:/Users/***/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/FileUploadDownload/WEB-INF/UploadFiles");
	            request.setAttribute("filenames", filenames);
	            request.getRequestDispatcher("download.jsp").forward(request,response);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    public static ArrayList<String> readfile(String filepath) throws FileNotFoundException, IOException {
	        ArrayList<String> filenames = new ArrayList<String>();
	        try {
	            File file = new File(filepath);
	            if (!file.isDirectory()) {
	                filenames.add(file.getName());
	            } else if (file.isDirectory()) {
	                String[] filelist = file.list();
	                for (int i = 0; i < filelist.length; i++) {
	                    File readfile = new File(filepath + "\\" + filelist[i]);
	                    if (!readfile.isDirectory()) {
	                        filenames.add(readfile.getName());
	                    } else if (readfile.isDirectory()) {
	                        filenames.addAll(readfile(filepath + "\\" + filelist[i]));
	                    }
	                }
	            }
	        } catch (FileNotFoundException e) {
	            System.out.println("readfile()   Exception:" + e.getMessage());
	        }
	        return filenames;
	    }
	    
	    /**
	     * ��ȡĳ���ļ����µ������ļ�
	     */
	    /* 
	    public static boolean readfile(String filepath) throws FileNotFoundException, IOException {
	        try {
	            File file = new File(filepath);
	            if (!file.isDirectory()) {
	                //System.out.println("�ļ�");
	                //System.out.println("path=" + file.getPath());
	                //System.out.println("absolutepath=" + file.getAbsolutePath());
	                //System.out.println("name=" + file.getName());
	            } else if (file.isDirectory()) {
	                //System.out.println("�ļ���");
	                String[] filelist = file.list();
	                for (int i = 0; i < filelist.length; i++) {
	                    File readfile = new File(filepath + "\\" + filelist[i]);
	                    if (!readfile.isDirectory()) {
	                        //System.out.println("path=" + readfile.getPath());
	                        //System.out.println("absolutepath=" + readfile.getAbsolutePath());
	                    	System.out.println("name=" + readfile.getName());

	                    } else if (readfile.isDirectory()) {
	                        readfile(filepath + "\\" + filelist[i]);
	                    }
	                    
	                }
	            }
	        } catch (FileNotFoundException e) {
	            System.out.println("readfile()   Exception:" + e.getMessage());
	        }
	        return true;
	    }
	     */
	        /**
	         * ɾ��ĳ���ļ����µ������ļ��к��ļ�
	         */
	        /*public static boolean deletefile(String delpath)
	                        throws FileNotFoundException, IOException {
	                try {

	                        File file = new File(delpath);
	                        if (!file.isDirectory()) {
	                                System.out.println("1");
	                                file.delete();
	                        } else if (file.isDirectory()) {
	                                System.out.println("2");
	                                String[] filelist = file.list();
	                                for (int i = 0; i < filelist.length; i++) {
	                                        File delfile = new File(delpath + "\\" + filelist[i]);
	                                        if (!delfile.isDirectory()) {
	                                                System.out.println("path=" + delfile.getPath());
	                                                System.out.println("absolutepath="
	                                                                + delfile.getAbsolutePath());
	                                                System.out.println("name=" + delfile.getName());
	                                                delfile.delete();
	                                                System.out.println("ɾ���ļ��ɹ�");
	                                        } else if (delfile.isDirectory()) {
	                                                deletefile(delpath + "\\" + filelist[i]);
	                                        }
	                                }
	                                file.delete();

	                        }

	                } catch (FileNotFoundException e) {
	                        System.out.println("deletefile()   Exception:" + e.getMessage());
	                }
	                return true;
	        }*/
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        doGet(request, response);
	    }
}
