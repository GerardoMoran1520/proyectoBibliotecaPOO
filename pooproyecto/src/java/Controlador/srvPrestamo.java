/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DAOCARGARUSU;
import Modelo.DAOCARGARDOCU;
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
@WebServlet(name = "srvPrestamo", urlPatterns = {"/srvPrestamo"})
public class srvPrestamo extends HttpServlet {

 
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
                        registrarPrestamo(request, response);
                        break;
                   
                    
                    case "listarPrestamos":
                        listarPrestamos(request, response);
                        break;    
                        
   
                    default:
                        response.sendRedirect("identificar.jsp");
                }
            }else if (request.getParameter("cambiar") != null) {
                cambiarEstado(request, response);
            
                
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
 private void cargarUsuarios(HttpServletRequest request) {
        DAOCARGARUSU dao = new DAOCARGARUSU();
        List<usuario> car = null;
        try {
            car = dao.listarUsuarios();
            request.setAttribute("cargarUsuarios", car);
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo cargar los usuarios " + e.getMessage());
        } finally {
            car = null;
            dao = null;
        }
    }
 
  private void cargarDocumentos(HttpServletRequest request) {
        DAOCARGARDOCU dao = new DAOCARGARDOCU();
        List<documento> car = null;
        try {
            car = dao.listarDocumentos();
            request.setAttribute("cargarDocumentos", car);
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo cargar los documentos " + e.getMessage());
        } finally {
            car = null;
            dao = null;
        }
    }
    private void presentarFormulario(HttpServletRequest request, HttpServletResponse response) {
     
        try {
            this.cargarDocumentos(request);
            this.cargarUsuarios(request);
            this.getServletConfig().getServletContext()
                    .getRequestDispatcher("/vistas/registrarPrestamo.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo cargar la vista");
        }
     
    }

  private void registrarPrestamo(HttpServletRequest request, HttpServletResponse response) {
    DAOPRESTAMO daoPres;
    prestamo pres = null;
    usuario usuar;
    documento docu;
    if (request.getParameter("cboUser") != null
            && request.getParameter("cboDocumento") != null
             && request.getParameter("txtPrestamo") != null
            && request.getParameter("txtDevolucion") != null
       ) {

        pres = new prestamo();
        pres.setFechaPrestamo(request.getParameter("txtPrestamo"));
        pres.setFechaDevolucion(request.getParameter("txtDevolucion"));
        usuar = new usuario();
        usuar. setId_usuario(Integer.parseInt(request.getParameter("cboUser")));
        pres.setNombreUsuario(usuar);
        docu = new documento();
        docu. setIdDocumento(Integer.parseInt(request.getParameter("cboDocumento")));
        pres.setTitulo(docu); 
         if (request.getParameter("chkEstado") != null) {
                pres.setEstado(true);
            } else {
                pres.setEstado(false);
            }
        daoPres = new  DAOPRESTAMO();
        try {
            daoPres.registrarPrestamo(pres);
            response.sendRedirect("srvPrestamo?accion=listarPrestamos");
        } catch (Exception e) {
            request.setAttribute("msje",
                    "No se pudo registrar el prestamo" + e.getMessage());
            request.setAttribute("prestamos", pres);
            this.presentarFormulario(request, response);
        }
    }
}


   private void listarPrestamos(HttpServletRequest request, HttpServletResponse response) {
    DAOPRESTAMO daoPrestamo = new DAOPRESTAMO();
    List<prestamo> prestamos = null;
    try {
        prestamos = daoPrestamo.listarPrestamos();
        request.setAttribute("prestamos", prestamos);
    } catch (Exception e) {
        request.setAttribute("msje", "No se pudo listar los préstamos" + e.getMessage());
    } finally {
        daoPrestamo = null;
    }
    try {
        this.getServletConfig().getServletContext().getRequestDispatcher("/vistas/listarPrestamos.jsp").forward(request, response);
    } catch (Exception ex) {
        request.setAttribute("msje", "No se pudo realizar la petición" + ex.getMessage());
    }
}
    
  private void cambiarEstado(HttpServletRequest request, HttpServletResponse response) {
           DAOPRESTAMO dao;
        prestamo pres;
        try {
            dao = new DAOPRESTAMO();
            pres = new prestamo();

            if (request.getParameter("cambiar").equals("activar")) {
                pres.setEstado(true);
            } else {
                pres.setEstado(false);
            }

            if (request.getParameter("cod") != null) {
                pres.setIdPrestamo(Integer.parseInt(request.getParameter("cod")));
                dao.cambiarVigencia(pres);
            } else {
                request.setAttribute("msje", "No se obtuvo el id del prestamo");
            }

        } catch (Exception e) {
            request.setAttribute("msje", e.getMessage());
        }
        this.listarPrestamos(request, response);
    }
    
}
