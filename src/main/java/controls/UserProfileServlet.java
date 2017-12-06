package controls;
import modules.*;
import DB.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/UserProfileServlet")
public class UserProfileServlet extends HttpServlet {

    HttpServletRequest request;
    private static final long serialVersionUID = 1L;


    private static final String UPLOAD_DIRECTORY = "Usrimg";

    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        User user =(User) request.getSession().getAttribute("user");
        if(user.isOwner()){
            updateOwnerProfile(user,request);
        }else
        {
            updateUserProfile(user,request);
        }
        request.setAttribute("messageup",
                "Successfully updated ");
        // jump to index
        getServletContext().getRequestDispatcher("/index.jsp").forward(
                request, response);
    }
    public void updateUserProfile(User usr, HttpServletRequest request){
        UserDB udb = new UserDB();
        HashMap<String, String> hm = save(request,usr);
        boolean change = false;
        if(hm.get("change") !=null ){
            change = true;
        }
        usr.setFirstName(hm.get("firstName"));
        usr.setLastName(hm.get("lastName"));
        if(change){

            usr = new Owner(usr);
            ((Owner)usr).fillOwnerPart(hm.get("lo"),hm.get("url"),0,hm.get("ph"),hm.get("description"));
            udb.updateUser(usr,true);
            request.getSession().setAttribute("user",usr);
        }else{
            udb.updateUser(usr,false);
        }

    }

    public void updateOwnerProfile(User usr,HttpServletRequest request) {
        UserDB udb = new UserDB();
        HashMap<String, String> hm = save(request,usr);
        usr.setFirstName(hm.get("firstName"));
        usr.setLastName(hm.get("lastName"));
        ((Owner)usr).setHost_location(hm.get("lo"));
        ((Owner)usr).setPhone(hm.get("ph"));
        ((Owner)usr).setHost_picture_url(hm.get("url"));
        ((Owner)usr).setDescription(hm.get("description"));
        udb.updateUser(usr,false);
    }


    public HashMap<String, String> save(HttpServletRequest request,User user){

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(factory);

        upload.setFileSizeMax(MAX_FILE_SIZE);

        upload.setSizeMax(MAX_REQUEST_SIZE);

        upload.setHeaderEncoding("UTF-8");
        String url = null;
        String uploadPath = request.getServletContext().getRealPath(".") + File.separator + UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        HashMap<String, String> reque = new HashMap<String, String>();
        try {
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(request);


            if (formItems != null && formItems.size() > 0) {
                //loop in from
                for (FileItem item : formItems) {

                    if (!item.isFormField()) {
                        if (item.getSize() > 100) {
                            String fileName = new File(item.getName()).getName();
                            fileName = user.getUserID() + dateFormat.format(new Date()).toString().replace("/", "").replace(":", "") + fileName;
                            fileName = fileName.replaceAll("[^1-9a-zA-Z./]+", "");
                            fileName.replace(" ", "");
                            String filePath = uploadPath + File.separator + fileName;
                            url =("Usrimg/" + fileName);
                            reque.put("url",url);
                            File storeFile = new File(filePath);

                            item.write(storeFile);
                            request.setAttribute("message",
                                    "success!");
                        }
                    } else {
                        reque.put(item.getFieldName(), item.getString());
                    }
                }
            }

        } catch (Exception ex) {
            System.out.print(ex.toString());
        }

        return reque;
    }

}
