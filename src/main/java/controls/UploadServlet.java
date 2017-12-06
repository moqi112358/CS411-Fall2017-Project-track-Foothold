package controls;
import DB.HouseDB;
import modules.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    private static final String UPLOAD_DIRECTORY = "img";

    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
         List<String> urls = new ArrayList<String>();
         List<String> names = new ArrayList<String>();
         boolean estimate = false;
         if(request.getParameter("estimate")!=null){
             estimate = true;
         }
        User user ;

        if (!ServletFileUpload.isMultipartContent(request)) {
            PrintWriter writer = response.getWriter();
            writer.println("Error: form need include enctype=multipart/form-data");
            writer.flush();
            return;
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");


        user = (User)request.getSession().getAttribute("user");
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(factory);

        upload.setFileSizeMax(MAX_FILE_SIZE);

        upload.setSizeMax(MAX_REQUEST_SIZE);

        upload.setHeaderEncoding("UTF-8");

        String uploadPath = getServletContext().getRealPath(".") + File.separator + UPLOAD_DIRECTORY;

        List<String> delet = new ArrayList<String>() ;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        Map<String,String> reque = new HashMap<String,String>();
        boolean hasThum = false;
        boolean edit = false;
        try {
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(request);


            if (formItems != null && formItems.size() > 0) {
                //loop in from
                for (FileItem item : formItems) {

                    if (!item.isFormField()) {
                        if(item.getSize()>100) {
                            if(item.getFieldName().toString().equals("thum")){
                                hasThum=true;
                            }
                            String fileName = new File(item.getName()).getName();
                            names.add(fileName);

                            fileName = user.getUserID()+dateFormat.format(new Date()).toString().replace("/", "").replace(":", "")+ fileName;
                            fileName=fileName.replaceAll("[^1-9a-zA-Z./]+", "");
                            fileName.replace(" ","");
                            String filePath = uploadPath + File.separator +    fileName;

                            urls.add("img/"+fileName);

                            File storeFile = new File(filePath);
                            // print path

                            // save the file
                            item.write(storeFile);
                            request.setAttribute("message",
                                    "success!");
                        }
                    }else{

                        // the item not in form
                        if(item.getFieldName().equals("deletePics")){
                            delet.add(item.getString());
                        }

                        reque.put(item.getFieldName(),item.getString());
                    }
                }
            }
        } catch (Exception ex) {
            System.out.print(ex.toString());
            request.setAttribute("messageup",
                    "error occurred please try again");
            getServletContext().getRequestDispatcher("/uploadHouse.jsp").forward(
                    request, response);
        }

        edit =reque.get("edit")!=null;

        String url =null;
        if(hasThum) {
            url = urls.get(0);
            urls.remove(0);
            names.remove(0);
        }
        HouseBean  hb  = new HouseBean(reque,urls,names,url,user);


        try {
            if (!edit) {

                if (hb == null) {
                    request.setAttribute("messageup",
                            "error occurred please try again");
                    getServletContext().getRequestDispatcher("/uploadHouse.jsp").forward(
                            request, response);
                }
                HouseDB hd = new HouseDB();
                hd.uploadHouse(hb);
                request.setAttribute("messageup",
                        "Successfully uploaded: ");
                // jump to index
                getServletContext().getRequestDispatcher("/index.jsp").forward(
                        request, response);
            } else {
                HouseBean oldhb = (HouseBean) request.getSession().getAttribute("hb");
                if (!hasThum)
                    hb.setThumbnail_url(oldhb.getThumbnail_url());
                hb.setHouseId(oldhb.getHouseId());
                HouseDB hd = new HouseDB();

                hd.updateHouse(hb, delet);
            }
        }catch(SQLException e ){

            }
            request.setAttribute("edit","true");
            if (hb == null) {
                request.setAttribute("messageup",
                        "error occurred please try again");
                getServletContext().getRequestDispatcher("/uploadHouse.jsp").forward(
                        request, response);
            }
            request.setAttribute("messageup",
                    "Successfully uploaded");
            // jump to index
            getServletContext().getRequestDispatcher("/index.jsp").forward(
                    request, response);
        }
    }

