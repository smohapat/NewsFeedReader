/**
 * File: NewsJqueryAjax.java
 * @author: Sourav Mohapatra
 * Description - Servlet to fetch the appropriate RSS feed based upon user's selection
 * 
 */
package cmu.edu.jquery.ajax;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author Sourav
 */
public class NewsJqueryAjax extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws TransformerConfigurationException
     * @throws TransformerException  
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, TransformerConfigurationException, TransformerException {

        String newsType = request.getParameter("search");//get news type
        String source = request.getParameter("source");//get news source
         response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        // get the xsl stored in this project 
        ServletContext context = getServletContext();
        System.out.println(context.getContextPath());
        InputStream xsl = (InputStream) (context.getResourceAsStream("/NewsFeed.xsl"));//Input Stream for the XS document on context path
        try {
            Source xmlDoc = getURL(newsType, source);//get the RSS document from source
            Source xslDoc = new StreamSource(xsl);//XSL to validate the above RSS document
            Result result = new StreamResult(out);//Result stream

            // Prepare to transform 
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer trans = factory.newTransformer(xslDoc);//Create transformer of XSL
            trans.transform(xmlDoc, result);//Transform the RSS using XSLT
            
        } finally {
           out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(NewsJqueryAjax.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(NewsJqueryAjax.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(NewsJqueryAjax.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(NewsJqueryAjax.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
     /**
     * Return Source object for search request url
     * @param search The URL string
     * @param newsSource News source name
     * @return Source object
     * @throws MalformedURLException  
     */
    public Source getURL(String search, String newsSource) throws MalformedURLException {
        //  URL url;
        Source source;
        // Create a URL objectfor the desired page 
        if (search.equals("Business")) // url = new URL("http://feeds.nytimes.com/nyt/rss/Business"); 
        {   
            if(newsSource.equals("BBC"))
            source = new StreamSource("http://feeds.bbci.co.uk/news/business/rss.xml");
            else if(newsSource.equals("SMH"))
            source = new StreamSource("http://www.smh.com.au/rssheadlines/business.xml");     
            else
            source = new StreamSource("http://feeds.nytimes.com/nyt/rss/Business");
        } else if(search.equals("Technology")){ //   url = new URL("http://feeds.nytimes.com/nyt/rss/Technology");
        
            if(newsSource.equals("BBC"))
           source = new StreamSource("http://feeds.bbci.co.uk/news/technology/rss.xml");
            else if(newsSource.equals("SMH"))
           source = new StreamSource("http://feeds.smh.com.au/rssheadlines/technology.xml");
           else
            source = new StreamSource("http://feeds.nytimes.com/nyt/rss/Technology");
        } else //    url = new URL("http://www.nytimes.com/services/xml/rss/nyt/World.xml");
        {  
             
            
             if(newsSource.equals("BBC"))
           source = new StreamSource("http://feeds.bbci.co.uk/news/world/rss.xml");
            else if(newsSource.equals("SMH"))
           source = new StreamSource("http://feeds.smh.com.au/rssheadlines/world.xml");
           else
            source = new StreamSource("http://www.nytimes.com/services/xml/rss/nyt/World.xml");
        }
        return source;

    }
}
