<%-- 
    Document   : registrarUsuario
    Created on : 12-02-2023, 11:26:45 PM
    Author     : drago
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    if (session.getAttribute("administrador") != null) {
%>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Biblioteca| Registrar Usuario</title>
        <!-- Tell the browser to be responsive to screen width -->
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <link rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap.min.css">
        <!-- Font Awesome -->
        <link rel="stylesheet" href="bower_components/font-awesome/css/font-awesome.min.css">
        <!-- Ionicons -->
        <link rel="stylesheet" href="bower_components/Ionicons/css/ionicons.min.css">
        <!-- Theme style -->
        <link href="dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css"/>

        <!-- AdminLTE Skins. We have chosen the skin-blue for this starter
              page. However, you can choose any other skin. Make sure you
              apply the skin class to the body tag so the changes take effect. -->
        <link rel="stylesheet" href="dist/css/skins/skin-blue.min.css">
        <link rel="stylesheet"
              href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
    </head>

    <body class="hold-transition skin-blue sidebar-mini">
        <div class="wrapper">

            <!-- Main Header -->
           <header class="main-header">
                <a href="#" class="logo">
                    <!-- mini logo for sidebar mini 50x50 pixels -->
                    <span class="logo-mini"><b>B</b>ADB</span>
                    <!-- logo for regular state and mobile devices -->
                    <span class="logo-lg"><b>Biblioteca</b> ADB</span>
                </a>

                <!-- Header Navbar -->
                <nav class="navbar navbar-static-top" role="navigation">
                    <!-- Sidebar toggle button-->
                    <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
                        <span class="sr-only">Toggle navigation</span>
                    </a>
                    <!-- Navbar Right Menu -->
                    <div class="navbar-custom-menu">
                        <ul class="nav navbar-nav">
                            <!-- User Account Menu -->
                            <li class="dropdown user user-menu">
                                <!-- Menu Toggle Button -->
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    <!-- The user image in the navbar-->
                                    
                                    <!-- hidden-xs hides the username on small devices so only the image appears. -->
                                    <span class="hidden-xs">${administrador.nombreUsuario}</span>
                                </a>
                                <ul class="dropdown-menu">
                                    <!-- The user image in the menu -->
                                    <li class="user-header">
                                        

                                        <p>                    
                                            BIENVENIDO   - ${administrador.nombreUsuario} <br><br>
                                            ROL: ${administrador.cargo.nombreCargo} 
                                        </p>
                                    </li>
                                    <!-- Menu Footer-->
                                    <li class="user-footer">
                                        <div class="pull-right">
                                            <a href="srvPrestamo?accion=cerrar" class="btn btn-primary btn-flat"><b>Cerrar Sesion</b></a>
                                        </div>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </nav>
            </header>
            <!-- Left side column. contains the logo and sidebar -->
             <aside class="main-sidebar">

                <!-- sidebar: style can be found in sidebar.less -->
                <section class="sidebar">

                   

            

                 <!-- Sidebar Menu -->
                    <ul class="sidebar-menu" data-widget="tree">
                        <li class="header "><center><b> INICIO</b></center></li>
                        <!-- Optionally, you can add icons to the links -->
                        <li class="active"><a href="#"><i class="fa fa-link"></i> <span>Panel Administrador</span></a></li>
                        <li class="treeview">
                            <a href="#"><i class="glyphicon glyphicon-th-large"></i> <span>Registros Usuarios</span>
                                <span class="pull-right-container">
                                    <i class="fa fa-angle-left pull-right"></i>
                                </span>
                            </a>
                            <ul class="treeview-menu">
                             
                             <li class="active"><a href="srvUsuario?accion=listarUsuarios"><i class="fa fa-address-card"></i>Usuarios</a></li>

                            </ul>
                        </li>
                        <li class="treeview">
                            <a href="#"><i class="glyphicon glyphicon-th-large"></i> <span>Registros Documentos</span>
                                <span class="pull-right-container">
                                    <i class="fa fa-angle-left pull-right"></i>
                                </span>
                            </a>
                            <ul class="treeview-menu">
                              <li class="active"><a href="srvDocumento?accion=listarDocumentos"><i class="fa fa-archive"></i>Documentos</a></li>
                           

                            </ul>
                        </li>
                        <li class="treeview">
                            <a href="#"><i class="fa fa-cart-arrow-down"></i> <span> Registros Prestamos</span>
                                <span class="pull-right-container">
                                    <i class="fa fa-angle-left pull-right"></i>
                                </span>
                            </a>
                            <ul class="treeview-menu">
                               <li class="active"><a href="srvPrestamo?accion=listarPrestamos"><i class="fa fa-cart-arrow-down"></i>Prestamos</a></li>
                              
                            </ul>
                        </li>
                       
                    </ul>
                    <!-- /.sidebar-menu -->
                </section>
                <!-- /.sidebar -->
            </aside>

            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <section class="content-header">
                    <div class="row">
                        <div class="col-xs-12 col-md-3">
                        </div>
                        <div class="col-md-3 hidden-xs"></div>
                        <div class="col-xs-2 col-md-1">
                        </div>
                        <div class="col-xs-10 col-md-5 ">
                            <div class="btn-group pull-right">
                                <a href="srvPrestamo?accion=listarPrestamos" class="btn btn-default">
                                    <i class="fa fa-align-justify"></i> Ver listado</a>                                              
                            </div>
                        </div>
                    </div>
                </section>
                <section class="content">
                    <div class="box">
                        <div class="box-header with-border">
                            <i class="fa fa-edit"></i> <h3 class="box-title">Registrar Nuevo Prestamo</h3>  
                        </div>
                        <form class="form-horizontal" action="srvPrestamo?accion=registrar" method="post">
                            <div class="box-body">
                              
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Fecha Prestamo</label>
                                    <div class="col-sm-4 input-group">
                                        <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                        <input id="prestamo" type="text" class="form-control" placeholder="Ejem: 28/08/23" name="txtPrestamo" maxlength="10"
                                               value="" required>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Fecha Devolucion</label>
                                    <div class="col-sm-4 input-group">
                                        <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                        <input id="devolucion" type="text" class="form-control" placeholder="Ejem: 06/09/23" name="txtDevolucion" maxlength="10"
                                               value="" required>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Nombre De Usuario </label>
                                    <div class="col-sm-4 input-group">
                                        <span class="input-group-addon"><i class="fa fa-tags"></i></span>
                                        <select class="form-control"  name="cboUser" autofocus=""  required="">
                                            <option value="0">Seleccione un usuario</option>
                                            <c:forEach items="${cargarUsuarios}" var="car1">
                                                <option value="${car1.id_usuario}"  
                                                        <c:if test="${car1.id_usuario == 
                                                                      usuario.usuario.id_usuario}">
                                                              selected
                                                        </c:if>
                                                        >${car1.nombreUsuario}</option>
                                            </c:forEach>
                                        </select>
                                    </div>                                  
                                </div>
                                
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Nombre De Documento </label>
                                    <div class="col-sm-4 input-group">
                                        <span class="input-group-addon"><i class="fa fa-tags"></i></span>
                                        <select class="form-control"  name="cboDocumento" autofocus=""  required="">
                                            <option value="0">Seleccione un documento</option>
                                            <c:forEach items="${cargarDocumentos}" var="car">
                                                <option value="${car.idDocumento}"  
                                                        <c:if test="${car.idDocumento == 
                                                                      documento.documento.idDocumento}">
                                                              selected
                                                        </c:if>
                                                        >${car.titulo}</option>
                                            </c:forEach>
                                        </select>
                                    </div>                                  
                                </div>
                                  <div class="form-group">
                                    <div class="col-sm-offset-2 col-sm-10">
                                        <div class="checkbox">
                                            <label>
                                                <input type="checkbox" name="chkEstado" checked=""> Activo
                                            </label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- /.box-body -->
                            <div class="box-footer">
                                <button type="reset" class="btn btn-danger"><i class="fa fa-close red"></i> Limpiar</button>
                                <button type="submit" id="" name="btnRegistrar" value="Registrar" class="btn btn-success"><i class="fa fa-floppy-o"></i> Registrar</button>

                            </div>
                            <!-- /.box-footer -->
                        </form>
                    </div>
                </section> 

            </div>
            <!-- /.content-wrapper -->

            <!-- Main Footer -->
             <footer class="main-footer">
                <!-- To the right -->
               
                <!-- Default to the left -->
                <strong>Copyright &copy; 2023 <a href="#">Biblioteca Amigos De Don Bosco</a>.</strong> Todos los derechos reservados.
            </footer>

            <div class="control-sidebar-bg"></div>
        </div>
        <!-- ./wrapper -->

        <!-- REQUIRED JS SCRIPTS -->

        <!-- jQuery 3 -->
        <script src="bower_components/jquery/dist/jquery.min.js"></script>
        <!-- Bootstrap 3.3.7 -->
        <script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
        <!-- AdminLTE App -->
        <script src="dist/js/adminlte.min.js"></script>

        <!-- Optionally, you can add Slimscroll and FastClick plugins.
             Both of these plugins are recommended to enhance the
             user experience. -->
    </body>
</html>
<%
    } else {
        response.sendRedirect("identificar.jsp");
    }
%>
