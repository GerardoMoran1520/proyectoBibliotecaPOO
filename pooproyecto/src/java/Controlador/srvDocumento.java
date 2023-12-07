/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DAOCARGARUSU;
import Modelo.DAOCARGARDOCU;
import Modelo.DAODOCUMENTO;
import Modelo.DAOPRESTAMO;
import Modelo.DAOUSUARIO;
import Modelo.documento;
import Modelo.prestamo;
import Modelo.usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author drago
 */
@WebServlet(name = "srvDocumento", urlPatterns = {"/srvDocumento"})
public class srvDocumento extends HttpServlet {

 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String accion = request.getParameter("accion");
        try {
            if (accion != null) {
                switch (accion) {
                    case "verificar":
                        verificar(request, response);
                        break;
                    case "cerrar":
                        cerrarsession(request, response);
                        break;
                     case "nuevo":
                        presentarFormulario(request, response);
                        break;
                      
                    case "registrar":
                        registrarDocumento(request, response);
                        break;
                   
                    
                    case "listarDocumentos":
                        listarDocumentos(request, response);
                        break;    
                        
   
                    default:
                        response.sendRedirect("identificar.jsp");
                }
            } else {
                response.sendRedirect("identificar.jsp");
            }
        } catch (Exception e) {
            try {
                this.getServletConfig().getServletContext().getRequestDispatcher("/mensaje.jsp").forward(request, response);

            } catch (Exception ex) {
                System.out.println("Error" + e.getMessage());
            }
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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

    private void verificar(HttpServletRequest request, HttpServletResponse response) throws Exception{
        HttpSession sesion;
        DAOUSUARIO dao;
        usuario usuario;
        usuario = this.obtenerUsuario(request);
        dao = new DAOUSUARIO();
        usuario = dao.identificar(usuario);
        if (usuario != null && usuario.getCargo().getNombreCargo().equals("ADMINISTRADOR")) {
            sesion = request.getSession();
            sesion.setAttribute("administrador", usuario);
            request.setAttribute("msje", "Bienvenido al sistema");
            this.getServletConfig().getServletContext().getRequestDispatcher("/vistas/formAdministrador.jsp").forward(request, response);
        }else if(usuario != null && usuario.getCargo().getNombreCargo().equals("ESTUDIANTE")){
           sesion = request.getSession();
            sesion.setAttribute("estudiante", usuario);
            this.getServletConfig().getServletContext().getRequestDispatcher("/vistas/formEstudiante.jsp").forward(request, response); 
        }else if(usuario != null && usuario.getCargo().getNombreCargo().equals("DOCENTE")){
           sesion = request.getSession();
            sesion.setAttribute("docente", usuario);
            this.getServletConfig().getServletContext().getRequestDispatcher("/vistas/formDocente.jsp").forward(request, response); 
        }else{
            request.setAttribute("msje", "Credenciales Incorrectas");
            request.getRequestDispatcher("identificar.jsp").forward(request, response);
        }
            
    }

     private void cerrarsession(HttpServletRequest request, HttpServletResponse response) throws Exception{
        HttpSession sesion = request.getSession();
        sesion.setAttribute("usuario", null);
        sesion.invalidate();
        response.sendRedirect("identificar.jsp");
        
    }
    
    private usuario obtenerUsuario(HttpServletRequest request) {
        usuario u = new usuario();
        u.setNombreUsuario(request.getParameter("txtUsu"));
        u.setClave(request.getParameter("txtPass"));
        return u;
    }


    private void presentarFormulario(HttpServletRequest request, HttpServletResponse response) {
     
        try {
           
            this.getServletConfig().getServletContext()
                    .getRequestDispatcher("/vistas/registrarDocumento.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo cargar la vista");
        }
     
    }

   private void registrarDocumento(HttpServletRequest request, HttpServletResponse response) {
        DAODOCUMENTO daoDoc;
        documento doc = null;
       
        if (request.getParameter("txtTitulo") != null
                && request.getParameter("txtAutor") != null
                 && request.getParameter("txtTipo") != null                                     
                 && request.getParameter("txtUbicacion") != null
                 && request.getParameter("txtTotal") != null
                 && request.getParameter("txtDisponible") != null
                 && request.getParameter("txtAnio") != null
  
                ) {

            doc = new documento();
            doc.setTitulo(request.getParameter("txtTitulo"));
            doc.setAutor(request.getParameter("txtAutor"));
            doc.setTipoDocumento(request.getParameter("txtTipo"));
            doc.setUbicacionFisica(request.getParameter("txtUbicacion"));
            doc.setCantidadTotal(Integer.parseInt(request.getParameter("txtTotal")));
            doc.setCantidadDisponible(Integer.parseInt(request.getParameter("txtDisponible")));
            doc.setAnioPublicacion(Integer.parseInt(request.getParameter("txtAnio")));
            if (request.getParameter("chkEstado") != null) {
                doc.setEstado(false);
            } else {
                doc.setEstado(true);
            }
            daoDoc = new DAODOCUMENTO();
            try {
                daoDoc.registrarDocumento(doc);
                response.sendRedirect("srvDocumento?accion=listarDocumentos");
            } catch (Exception e) {
                request.setAttribute("msje",
                        "No se pudo registrar el documento" + e.getMessage());
                request.setAttribute("documento", doc);
                this.presentarFormulario(request, response);
            }
        }
    }

   private void listarDocumentos(HttpServletRequest request, HttpServletResponse response) {
        DAODOCUMENTO dao = new  DAODOCUMENTO();
        List<documento> docume = null;
        try {
            docume = dao.listarDocumentos();
            request.setAttribute("documentos", docume);

        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo listar los documentos" + e.getMessage());
        } finally {
            dao = null;
        }
        try {
            this.getServletConfig().getServletContext().getRequestDispatcher("/vistas/listarDocumentos.jsp").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("msje", "No se puedo realizar la petición" + ex.getMessage());
        }
    }
    

}
