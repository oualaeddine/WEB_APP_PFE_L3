<%@ page import="model.beans.views.TablesView" %>
<%@ page import="model.beans.views.nav.NavElement" %>
<%@ page import="model.beans.views.table.DataTableRow" %>
<%@ page import="static utils.MyConsts.FOOTER_COPYRIGHT" %>
<%@ page import="model.enums.UserType" %>
<%@ page import="control.servlets.MyServlet" %>
<%@ page import="model.beans.humans.Person" %>
<%@ page import="model.beans.views.MyView" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="model.beans.humans.Employe" %>
<%@ page import="model.beans.Localite" %>
<%@ page import="model.db.daos.LocaliteDAO" %>
<%@ page import="model.db.daos.EmployeDAO" %>
<%@ page import="model.beans.Visite" %>
<%@ page import="model.db.daos.VisitesDao" %>
<%--
  Created by IntelliJ IDEA.
  User: berre
  Date: 2/17/2018
  Time: 10:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%! private TablesView tablesView = new TablesView(); %>
<%
    UserType userType = (UserType) request.getSession().getAttribute(MyServlet.LOGGED_IN_USER_TYPE);
    Employe employe = (Employe) request.getSession().getAttribute(MyServlet.LOGGED_IN_USER);
    int userId = (int) request.getSession().getAttribute(MyServlet.LOGGED_IN_USER_ID);
    String currentPage = request.getParameter("page");
    tablesView.setLoggedInUserId(userId);
    tablesView.setLoggedInUserType(userType);
    tablesView.setCurrentPage(currentPage);
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title><%out.print(tablesView.getPageTitle());%></title>
    <!-- Bootstrap core CSS-->
    <link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom fonts for this template-->
    <link href="../vendor/font-awesome/css/fontawesome-all.min.css" rel="stylesheet" type="text/css">
    <link href="../vendor/font-awesome-old/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <!-- Page level plugin CSS-->
    <link href="../vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">
    <!-- Custom styles for this template-->
    <link href="../css/sb-admin.css" rel="stylesheet">


    <!-- fullCalendar -->
    <link rel="stylesheet" href="./programmerVisite/assets/fullcalendar/dist/fullcalendar.min.css">
    <link rel="stylesheet" href="./programmerVisite/assets/fullcalendar/dist/fullcalendar.print.min.css" media="print">

</head>

<body class="fixed-nav sticky-footer bg-dark" id="page-top">
<!-- Navigation-->

<nav class="navbar navbar-expand-lg navbar-dark navbar-<%out.print(tablesView.getNav().getCssBackgroundClass());%> sidebar fixed-top fixed-top "
     id="mainNav">
    <a class="navbar-brand"
       href="#"><%out.print(tablesView.getNav().getTitle() + ": " + employe.getNom() + " " + employe.getPrenom());%></a>
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse"
            data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false"
            aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav scroll-nav  navbar-sidenav" id="exampleAccordion">
            <% for (MyView navElement : tablesView.getNav().getElements()) {
                out.print(navElement.getHtml());
            }
            %>
        </ul>
        <ul class="navbar-nav sidenav-toggler">
            <li class="nav-item">
                <a class="nav-link text-center" id="sidenavToggler">
                    <i class="fa fa-fw fa-angle-left"></i>
                </a>
            </li>
        </ul>

    </div>
</nav>
<div class="content-wrapper">
    <div class="container-fluid">
        <!-- Breadcrumbs-->
        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="#"><%out.print(tablesView.getPageTitle());%></a>
            </li>
            <li class="breadcrumb-item active"><%out.print(tablesView.getBreadcrumbTitle());%></li>
        </ol>
        <!-- Example DataTables Card-->
        <div class="card mb-3">
            <div class="card-header">
                <i class="fa fa-table"></i> <%out.print(tablesView.getDataTable().getDataTableTitle());%>
            </div>
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                        <thead>
                        <tr>
                            <%out.print(tablesView.getDataTable().getTableHeader());%>
                        </tr>
                        </thead>
                        <tfoot>
                        <tr>
                            <%out.print(tablesView.getDataTable().getTableHeader());%>
                        </tr>
                        </tfoot>
                        <tbody>

                        <% LinkedList<DataTableRow> list = tablesView.getDataTable().getTableData();
                            for (DataTableRow tableRow : list) {
                                out.print(tableRow.getHtml());
                            }%>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="card-footer small text-muted">Mis à jour <strong>Maintenant</strong></div>
        </div>
    </div>
    <!-- /.container-fluid-->
    <!-- /.content-wrapper-->
    <footer class="sticky-footer">
        <div class="container">
            <div class="text-center">
                <%out.print(FOOTER_COPYRIGHT); %>
            </div>
        </div>
    </footer>
    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fa fa-angle-up"></i>
    </a>
    <!-- Logout Modal-->
    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                    <a class="btn btn-primary" href="login.html">Logout</a>
                </div>
            </div>
        </div>
    </div>

    <%--Modal assigner region--%>
    <div id="myModal" class="modal fade" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Sélectionner la région</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <div class="modal-body">
                    <form method="post" action="/AjoutServlet?ajouter=assignation">

                        <input id="agentId" name="agentInput" type="hidden">
                        <div class="form-group">
                            <label for="selectRegion">Sélectionner région</label>
                            <select class="custom-select" id="selectRegion" name="selectRegion">
                                <%LinkedList<Localite> localites = new LocaliteDAO().getAll();%>
                                <%
                                    for (Localite localite : localites) {
                                        out.print("<option value=\"" + localite.getId() + "\">" + localite.getNom() + "</option>\n");
                                    }
                                %>
                            </select>
                        </div>
                        <button class="btn btn-info btn-lg" type="submit">Confirmer</button>
                    </form>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>

        </div>
    </div>

    <%--Modal signaler client--%>
    <div id="signalerModal" class="modal fade" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Motif</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <div class="modal-body">
                    <form method="post" action="/AjoutServlet?ajouter=signalement" id="signalerForm">

                        <input id="clientSignale" name="clientInput" type="hidden">
                        <div class="form-group">
                            <label for="motif">Veuillez entrer le motif de votre signalement</label>
                            <textarea id="motif" rows="4" cols="50" name="comment" form="signalerForm" size="100">

                            </textarea>
                        </div>
                        <button class="btn btn-info btn-lg" type="submit">Valider</button>
                    </form>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>

        </div>
    </div>
    <%--Modal approuver employe--%>
    <div id="approuverModal" class="modal fade" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Confirmation</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <div class="modal-body">
                    <form method="post" action="/AjoutServlet?ajouter=approuvement" id="approuverForm">


                        <input id="employeApprouve" name="employeApprouve" type="hidden">
                        <div class="form-group">
                            <label for="userTypeInput">Type d'employé</label>
                            <select class="custom-select" name="userTypeInput" id="userTypeInput">
                                <option value="agent">Agent</option>
                                <option value="operateur">Operateur</option>
                                <option value="responsableVentes">Responsable ventes</option>
                                <%
                                    if (userType == UserType.SU) {
                                        out.print("<option value=\"admin\">Admin</option>");
                                    }
                                %>
                            </select>
                        </div>
                        <button class="btn btn-info btn-lg" type="submit">Valider</button>
                    </form>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Annuler</button>
                </div>
            </div>
        </div>
    </div>
    <%--Modal suspendre/réintegrer employé--%>
    <div id="suspendreModal" class="modal fade" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Confirmation</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <div class="modal-body">
                    <form method="post" action="/AjoutServlet?ajouter=suspend" id="suspendreForm">

                        <input id="employeSuspendu" name="employeSuspendu" type="hidden">
                        <div class="form-group">
                            <label>Êtes vous sur de vouloir suspendre/réintegrer cet employé ?</label>
                        </div>
                        <button class="btn btn-info btn-lg" type="submit">Valider</button>
                    </form>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Annuler</button>
                </div>
            </div>
        </div>
    </div>
    <%--Modal Geler/Dégeler logement--%>
    <div id="gelerModal" class="modal fade" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Confirmation</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <div class="modal-body">
                    <form method="post" action="/AjoutServlet?ajouter=gel" id="gelerForm">

                        <input id="logementGele" name="logementGele" type="hidden">
                        <div class="form-group">
                            <label>Êtes vous sur de vouloir geler/dégeler ce logement ?</label>
                        </div>
                        <button class="btn btn-info btn-lg" type="submit">Valider</button>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Annuler</button>
                </div>
            </div>
        </div>
    </div>

    <%--Modal Modifier/Annuler visite--%>
    <div id="modifierVisiteModal" class="modal fade" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Modifier visite</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <div class="modal-body">
                    <form method="get" action="/ModifierServlet?ajouter=visite" id="modifierVisiteForm">

                        <input id="visiteModifiee" name="nouvelleDate" type="hidden">
                        <div class="form-group">
                            <label for="motif">Veuillez entrer le motif de votre signalement</label>
                            <select name="action" id="action" class="form-control">
                                <option value="0" disabled="" selected="">- action -</option>
                                <option value="1"> cancel</option>
                                <option value="2"> reporter</option>
                            </select>
                            <div id='calendar'></div>
                        </div>
                        <button class="btn btn-info btn-lg" type="button" onclick="function modifierVisite() {
  var  action = $('#action option:selected').val();
                        if (action===1){
var params = {visiteId:          $('#visiteModifiee').val()  ,action:'rapport',etatVisite:'annulee'            };
                        post('/NewRapport', params, 'GET'); }
                        if (action===2){
var params = {visiteId:$('#visiteModifiee').val()  ,action:'rapport',etatVisite:'reportee'            };
                        post('/NewRapport', params, 'GET');
                        }
                        }
                        modifierVisite()">Valider
                        </button>
                    </form>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>

        </div>
    </div>


    <%--Modal Etablir Rapport--%>
    <div id="etablirRapportModal" class="modal fade" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Modifier visite</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <div class="modal-body">
                    <form method="post" action="/NewRapport" id="etablirRapportForm">

                        <input id="visiteRapport" name="visiteRapport" type="hidden">
                        <div class="form-group">
                            <label for="motif">Veuillez entrer le motif de votre signalement</label>
                            <select name="etatVisite" id="etatVisite" class="form-control">
                                <option value="validee"> Validée</option>
                                <option value="nonvalidee"> Non validée</option>
                                <option value="reportee">Reportée</option>
                                <option value="annulee">Annulée</option>
                            </select>
                        </div>
                        <button class="btn btn-info btn-lg" type="submit">Valider</button>
                    </form>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>

        </div>
    </div>
    <!-- Bootstrap core JavaScript-->
    <script src="../vendor/jquery/jquery.min.js"></script>
    <script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <!-- Core plugin JavaScript-->
    <script src="../vendor/jquery-easing/jquery.easing.min.js"></script>
    <!-- Page level plugin JavaScript-->
    <script src="../vendor/datatables/jquery.dataTables.js"></script>
    <script src="../vendor/datatables/dataTables.bootstrap4.js"></script>
    <!-- Custom scripts for all pages-->
    <script src="../js/sb-admin.min.js"></script>
    <!-- Custom scripts for this page-->
    <script src="../js/sb-admin-datatables.min.js"></script>

    <script>
        function getVisiteTaaLrapport(idTaaLaVisite) {
            document.getElementById("visiteRapport").value = idTaaLaVisite;

        }
        function getAgentId(idTaaLagent) {
            document.getElementById("agentId").value = idTaaLagent;
        }

        function getClientId(idTaaLClient) {
            document.getElementById("clientSignale").value = idTaaLClient;

        }

        function getApprovedId(idTaaLEmploye) {
            document.getElementById("employeApprouve").value = idTaaLEmploye;
        }

        function getSuspendedId(idTaaLemploye) {
            document.getElementById("employeSuspendu").value = idTaaLemploye;
        }

        function getLogementGeleId(idTaaLeLogement) {
            document.getElementById("logementGele").value = idTaaLeLogement;
        }

        function getVisiteModifieeId(idtaalavisite) {
            document.getElementById("visiteModifiee").value = idtaalavisite;
        }

    </script>

    <!-- fullCalendar -->
    <script src="./programmerVisite/assets/moment/moment.js"></script>
    <script src="./programmerVisite/assets/fullcalendar/dist/fullcalendar.min.js"></script>
    <script src="./programmerVisite/assets/fullcalendar/dist/locale/fr.js"></script>
    <script>


        function post(path, params, method) {
            method = method || "post"; // Set method to post by default if not specified.

            // The rest of this code assumes you are not using a library.
            // It can be made less wordy if you use one.
            var form = document.createElement("form");
            form.setAttribute("method", method);
            form.setAttribute("action", path);

            for (var key in params) {
                if (params.hasOwnProperty(key)) {
                    var hiddenField = document.createElement("input");
                    hiddenField.setAttribute("type", "hidden");
                    hiddenField.setAttribute("name", key);
                    hiddenField.setAttribute("value", params[key]);

                    form.appendChild(hiddenField);
                }
            }

            document.body.appendChild(form);
            form.submit();
        }

        var calendar = $('#calendar').fullCalendar({

                themeSystem: 'standard',
                defaultView: 'agendaWeek',

                header: {
                    left: 'prev,next today',
                    center: 'title',
                    right: ''
                },
                title: "choisissez une date",
                // defaultDate: '2018-03-12',
                weekNumbers: false,
                navLinks: false, // can click day/week names to navigate views
                editable: false,
                eventLimit: true, // allow "more" link when too many events
                hiddenDays: [6, 7], // hide Tuesdays and Thursdays
                selectable: true,
                unselectAuto: false,
                businessHours: {
                    // days of week. an array of zero-based day of week integers (0=Sunday)
                    dow: [0, 1, 2, 3, 4, 5], // Monday - Thursday

                    start: '8:00', // a start time (10am in this example)
                    end: '16:00' // an end time (6pm in this example)
                },
                // days of week. an array of zero-based day of week integers (0=Sunday)
                dow: [0, 1, 2, 3, 4, 5], // Monday - Thursday
                start: '8:00:00', // a start time (10am in this example)
                end: '16:00:00', // an end time (6pm in this example)
                minTime: '08:00:00',
                maxTime: '16:00:00',
                eventSources: [
                    /*    // your event source
                        {
                            url: '/api/visiteApi?action=getTakenDates&logementId=' + idLogement, // use the `url` property
                            color: 'red',    // an option!
                            textColor: 'black'  // an option!
                        }*/

                    // any other sources...
                ],
                eventColor: '#378006',
                displayEventTime: false,
                slotDuration: "02:00:00",
                eventClick: function (calEvent, jsEvent, view) {
                    return false;
                },
                select: function (startDate, endDate) {
                    $('#dateVisite').val(startDate.format());
                }
            })
        ;

        function initCalendar(idLogement) {
            var events = {
                url: '/api/visiteApi?action=getTakenDates&logementId=' + idLogement, // use the `url` property
                color: 'red',    // an option!
                textColor: 'black'  // an option!
            };
            calendar.fullCalendar('removeEventSources');
            calendar.fullCalendar('addEventSource', events);
            calendar.fullCalendar('refetchEvents');
        }
    </script>
</div>
</body>

</html>
