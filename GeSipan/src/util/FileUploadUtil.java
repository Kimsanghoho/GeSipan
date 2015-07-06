package util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.apache.tomcat.util.http.fileupload.FileUploadException;

public class FileUploadUtil  {
	 
    private boolean multipart = false;
    
    private HashMap parameterMap;
    private HashMap fileItemMap;
    
    public FileUploadUtil(HttpServletRequest request) 
    throws FileUploadException{
        this(request, -1, -1, null);
    }
    
    public FileUploadUtil(HttpServletRequest request,
        int threshold, int max, String repositoryPath) throws FileUploadException {
        //super(request);
        
        parsing(request, threshold, max, repositoryPath);
    }
    private void parsing(HttpServletRequest request,
        int threshold, int max, String repositoryPath) throws FileUploadException {
        
        if (true) {//FileUpload.isMultipartContent(request
        	
            multipart = true;
            
            parameterMap = new java.util.HashMap();
            fileItemMap = new java.util.HashMap();
            
          /*  DiskFileUpload diskFileUpload = new DiskFileUpload();
            if (threshold != -1) {
                diskFileUpload.setSizeThreshold(threshold);
            }
            diskFileUpload.setSizeMax(max);
            if (repositoryPath != null) {
                diskFileUpload.setRepositoryPath(repositoryPath);
            }
            
            java.util.List list = diskFileUpload.parseRequest(request);
            for (int i = 0 ; i < list.size() ; i++) {
                FileItem fileItem = (FileItem) list.get(i);
                String name = fileItem.getFieldName();
                
                if (fileItem.isFormField()) {
                    String value = fileItem.getString();
                    String[] values = (String[]) parameterMap.get(name);
                    if (values == null) {
                        values = new String[] { value };
                    } else {
                        String[] tempValues = new String[values.length + 1];
                        System.arraycopy(values, 0, tempValues, 0, 1);
                        tempValues[tempValues.length - 1] = value;
                        values = tempValues;
                    }
                    parameterMap.put(name, values);
                } else {
                    fileItemMap.put(name, fileItem);
                }
            }
            addTo();*/ // request 속성으로 설정한다.
        }
    }
    
    public boolean isMultipartContent() {
        return multipart;
    }
    
    public String getParameter(String name) {
        if (multipart) {
            String[] values = (String[])parameterMap.get(name);
            if (values == null) return null;
            return values[0];
        } else
            return null;
    }
    
    public String[] getParameterValues(String name) {
        if (multipart)
            return (String[])parameterMap.get(name);
        else
            return null;
    }
    
    public Enumeration getParameterNames() {
        if (multipart) {
            return new Enumeration() {
                Iterator iter = parameterMap.keySet().iterator();
                
                public boolean hasMoreElements() {
                    return iter.hasNext();
                }
                public Object nextElement() {
                    return iter.next();
                }
            };
        } else {
            return null;
        }
    }
    
    public Map getParameterMap() {
        if (multipart)
            return parameterMap;
        else
            return null;
    }
    
    public FileItem getFileItem(String name) {
        if (multipart)
            return (FileItem) fileItemMap.get(name);
        else
            return null;
    }
    
    /**
     * 관련된 FileItem 들의 delete() 메소드를 호출한다.
     */
    public void delete() {
        if (multipart) {
            Iterator fileItemIter = fileItemMap.values().iterator();
            while( fileItemIter.hasNext()) {
                FileItem fileItem = (FileItem)fileItemIter.next();
                fileItem.delete();
            }
        }
    }
    
    public void addTo() {
     //   super.setAttribute(FileUploadRequestWrapper.class.getName(), this);
    }
    /*
    public static FileUploadRequestWrapper 
                  getFrom(HttpServletRequest request) {
        return (FileUploadRequestWrapper)
            request.getAttribute(FileUploadRequestWrapper.class.getName());
    }
    
    public static boolean hasWrapper(HttpServletRequest request) {
        if (FileUploadRequestWrapper.getFrom(request) == null) {
            return false;
        } else {
            return true;
        }
    }*/
}
