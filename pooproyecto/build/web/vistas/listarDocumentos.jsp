<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    if (session.getAttribute("administrador") != null) {
%>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Biblioteca| Usuarios</title>
        <!-- Tell the browser to be responsive to screen width -->
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <link rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap.min.css">
        <!-- Font Awesome -->
        <link rel="stylesheet" href="bower_components/font-awesome/css/font-awesome.min.css">
        <!-- Ionicons -->
        <link rel="stylesheet" href="bower_components/Ionicons/css/ionicons.min.css">
        <!-- Theme style -->
        <link href="dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css"/>
        <link href="bower_components/datatables.net-bs/css/dataTables.bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="swetalert/sweetalert.css" rel="stylesheet" type="text/css"/>
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
                                            BIENVENIDO  - ${administrador.nombreUsuario} <br><br>
                                            ROL: ${administrador.cargo.nombreCargo} 
                                        </p>
                                    </li>
                                    <!-- Menu Footer-->
                                    <li class="user-footer">
                                        <div class="pull-right">
                                            <a href="srvUsuario?accion=cerrar" class="btn btn-primary btn-flat"><b>Cerrar Sesion</b></a>
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
            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <section class="content-header">
                    <h1>Página Documentos</h1>
                </section>
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <a href="srvDocumento?accion=nuevo" class="btn btn-success">
                        <i class="fa fa-plus"></i> Nuevo Documento </a>

                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> Inicio</a></li>
                        <li class="active">Documentos</li>
                    </ol>
                </section>

                <section class="content">
                    <div class="box">    
                        <div class="box-header with-border">             
                            <h3 class="box-title">Listado de Documentos</h3>
                        </div>
                        <div class="box-body">
                            <div class="table-responsive" >                                 
                                <table class="table table-bordered table-striped dataTable table-hover" id="tablaUsuarios" class="display">
                                    <thead>
                                        <tr>
                                            <th>IDDocumento</th>
                                            <th>titulo</th>
                                            <th>Autor</th>
                                            <th>Tipo Documento</th>
                                            <th>Ubicacion Fisica</th>
                                            <th>Cantidad Total</th>
                                            <th>Cantidad Disponible</th>
                                            <th>Año De Publicación</th>
                                            <th>Acciones</th> 
                                        </tr>
                                    </thead>
                                    <c:forEach var="docu" items="${documentos}" varStatus="iteracion">                                                    
                                        <tr>
                                            <td>${iteracion.index + 1}</td>
                                            <td>${docu.titulo}</td>
                                            <td>${docu.autor}</td>
                                            <td>${docu.tipoDocumento}</td>
                                            <td>${docu.ubicacionFisica}</td>
                                            <td>${docu.cantidadTotal}</td>
                                            <td>${docu.cantidadDisponible}</td>
                                            <td>${docu.anioPublicacion}</td>
                                            <c:if test="${user.estado == false}">
                                                <td><span class="badge bg-green active">Documento Disponible</span></td> 
                                            </c:if>
                                            <c:if test="${user.estado == true}">
                                                <td><span class="badge bg-red active">Documento No Disponible</span></td> 
                                            </c:if>
                                            
                                         <td><a href="<c:url value="srvDocumento">
                                                       <c:param name="accion" value="leerDocumento" />
                                                       <c:param name="cod" value="${docu.idDocumento}" />
                                                   </c:url>"><button type="button" class="btn btn-warning" data-toggle="tooltip"  title="Editar" data-original-title="Editar">
                                                        <i class="fa fa-pencil"></i></button></a>
                                                <!-- DESACTIVAR / ACTIVAR USUARIOS -->
                                                
                                                <!-- ELIMINAR USUARIOS -->
                                                <input type="hidden" id="codigo" value="${docu.idDocumento}">
                                                <a id="deleteDocer" href="<c:url value="srvDocumento">
                                                       <c:param name="accion" value="eliminarDocumento" />
                                                       <c:param name="cod" value="${docu.idDocumento}" />
                                                   </c:url>"><button type="button" class="btn btn-danger" data-toggle="tooltip"  title="Eliminar" data-original-title="Eliminar">
                                                        <i class="fa fa-trash"></i></button></a>

                                            </td>
                                        </tr>                                                    
                                    </c:forEach>                                               
                                </table>
                            </div>
                        </div>
                        <!-- /.box-body -->
                        <div class="box-footer">
                            <!--Pie de página-->
                        </div>
                        <!-- /.box-footer-->
                    </div>
                </section>
                <!-- /.content -->
            </div>
            <!-- /.content-wrapper -->

            <!-- Main Footer -->
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
        <script src="bower_components/datatables.net/js/jquery.dataTables.min.js" type="text/javascript"></script>
        <script src="bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js" type="text/javascript"></script>
        <script src="swetalert/sweetalert.js" type="text/javascript"></script>
        <script src="js/funcionesUsuario.js" type="text/javascript"></script>
        <script>
            $(document).ready(function () {
                $('#tablaUsuarios').DataTable();
            });
        </script>
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
