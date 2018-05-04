<%@ page import="control.servlets.MyServlet" %>
<%@ page import="control.statistics.globales.VisitesStats" %>
<%@ page import="control.statistics.perso.AgentStats" %>
<%@ page import="model.beans.Localite" %>
<%@ page import="model.beans.Rapport" %>
<%@ page import="model.beans.humans.Employe" %>
<%@ page import="model.db.daos.*" %>
<%@ page import="model.enums.EtatClient" %>
<%@ page import="java.time.Month" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.LinkedList" %>
<!DOCTYPE html>
<html>
<% Employe loggedAgent = (Employe) request.getSession().getAttribute(MyServlet.LOGGED_IN_USER);%>
<%String localite = new AssignationDAO().getLocaliteByAgent(loggedAgent.getId()).getNom();%>
<%
    AgentStats stats = new AgentStats(loggedAgent.getId());
%>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>AdminLTE 2 | Dashboard</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.7 -->
    <link rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="bower_components/font-awesome/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="bower_components/Ionicons/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="dist/css/AdminLTE.min.css">
    <!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="dist/css/skins/_all-skins.min.css">

    <!-- Google Font -->
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
</head>

<body class="hold-transition skin-blue ">
<div class="">
    <!-- Main content -->
    <section class="content">
        <!-- Info boxes -->
        <div class="row">
            <div class="col-md-3 col-sm-6 col-xs-12">
                <div class="info-box">
                    <span class="info-box-icon bg-aqua"><i class="ion ion-ios-gear-outline"></i></span>

                    <div class="info-box-content">
                        <span class="info-box-text">Total des Visites effectuées dans la region de <%out.print(localite);%></span>
                        <span class="info-box-number">907</span>
                    </div>
                    <!-- /.info-box-content -->
                </div>
                <!-- /.info-box -->
            </div>
            <!-- /.col -->
            <div class="col-md-3 col-sm-6 col-xs-12">
                <div class="info-box">
                    <span class="info-box-icon bg-red"><i class="fa fa-google-plus"></i></span>

                    <div class="info-box-content">
                        <span class="info-box-text">Total des Avis negatifs dans la region de <%out.print(localite);%></span>
                        <span class="info-box-number">370</span>
                    </div>
                    <!-- /.info-box-content -->
                </div>
                <!-- /.info-box -->
            </div>
            <!-- /.col -->

            <!-- fix for small devices only -->
            <div class="clearfix visible-sm-block"></div>

            <div class="col-md-3 col-sm-6 col-xs-12">
                <div class="info-box">
                    <span class="info-box-icon bg-green"><i class="ion ion-ios-cart-outline"></i></span>

                    <div class="info-box-content">
                        <span class="info-box-text">Total des Avis positifs dans la region de <%out.print(localite);%></span>
                        <span class="info-box-number">407</span>
                    </div>
                    <!-- /.info-box-content -->
                </div>
                <!-- /.info-box -->
            </div>
            <!-- /.col -->
            <div class="col-md-3 col-sm-6 col-xs-12">
                <div class="info-box">
                    <span class="info-box-icon bg-yellow"><i class="ion ion-ios-people-outline"></i></span>

                    <div class="info-box-content">
                        <span class="info-box-text">Total des Clients reçus dans la region de Constantine</span>
                        <span class="info-box-number">730</span>
                    </div>
                    <!-- /.info-box-content -->
                </div>
                <!-- /.info-box -->
            </div>
            <!-- /.col -->
        </div>
        <!-- /.row -->

        <div class="row">
            <div class="col-md-12">
                <div class="box">
                    <div class="box-header with-border">
                        <h3 class="box-title">Nombre de visites mensuel</h3>

                        <div class="box-tools pull-right">
                            <button type="button" class="btn btn-box-tool" data-widget="collapse"><i
                                    class="fa fa-minus"></i>
                            </button>
                            <button type="button" class="btn btn-box-tool" data-widget="remove"><i
                                    class="fa fa-times"></i></button>
                        </div>
                    </div>
                    <!-- /.box-header -->
                    <div class="box-body">
                        <div class="row">
                            <div class="col-md-8">
                                <p class="text-center">
                                    <strong>visites dans la region de Constantine: 1
                                        Jan, <%out.print(Calendar.getInstance().get(Calendar.YEAR));%> - 31 Decembre,
                                        <%out.print(Calendar.getInstance().get(Calendar.YEAR));%></strong>
                                </p>

                                <div class="chart">
                                    <!-- Sales Chart Canvas -->
                                    <canvas id="salesChart" style="height: 180px;"></canvas>
                                </div>
                                <!-- /.chart-responsive -->
                            </div>
                            <!-- /.col -->
                            <div class="col-md-4">
                                <p class="text-center">
                                    <strong>Mes statistiques</strong>
                                </p>

                                <!-- /.progress-group -->
                                <div class="progress-group">
                                    <span class="progress-text">Nombre de logements visités:</span>
                                    <span class="progress-number"><b><%out.print(stats.getVisitedLogementsNbr());%></b>/<%out.print(new LogementDAO().getAll().size());%></span>
                                    <div class="progress sm">
                                        <div class="progress-bar progress-bar-green"
                                             style="width: <%out.print(stats.logementsVisitesPercentage());%>%"></div>
                                    </div>
                                </div>
                                <div class="progress-group">
                                    <span class="progress-text">Nombre des avis positifs sur mes visites que j'ai effectué </span>
                                    <span class="progress-number"><b><%out.print(stats.positifsNbr());%></b>/<%out.print(stats.rapportsNbr());%></span>

                                    <div class="progress sm">
                                        <div class="progress-bar progress-bar-aqua"
                                             style="width: <%out.print(stats.positifPercentage());%>%"></div>
                                    </div>
                                </div>
                                <!-- /.progress-group -->
                                <div class="progress-group">
                                    <span class="progress-text">Nombre des avis negatifs sur mes visites </span>
                                    <span class="progress-number"><b><%out.print(stats.negatifsNbr());%></b>/<%out.print(stats.rapportsNbr());%></span>

                                    <div class="progress sm">
                                        <div class="progress-bar progress-bar-red"
                                             style="width: <%out.print(stats.negatifPercentage());%>%"></div>
                                    </div>
                                </div>

                                <!-- /.progress-group -->
                                <div class="progress-group">
                                    <span class="progress-text">Nombre de clients que j'ai reçu</span>
                                    <span class="progress-number"><b><%out.print(stats.getClients());%></b>/<%out.print(new ClientDAO().getAll().size());%></span>

                                    <div class="progress sm">
                                        <div class="progress-bar progress-bar-yellow"
                                             style="width: <%out.print(stats.clientsPercentage());%>%"></div>
                                    </div>
                                </div>
                                <!-- /.progress-group -->
                            </div>
                            <!-- /.col -->
                        </div>
                        <!-- /.row -->
                    </div>
                    <!-- ./box-body -->
                </div>
                <!-- /.box -->
            </div>
            <!-- /.col -->
        </div>
        <!-- /.row -->

        <!-- Main row -->
        <div class="row">
            <!-- Left col -->
            <div class="col-md-8">
                <!-- TABLE: LATEST ORDERS -->
                <div class="box box-info">
                    <div class="box-header with-border">
                        <h3 class="box-title">Mes derniers rapports</h3>

                        <div class="box-tools pull-right">
                            <button type="button" class="btn btn-box-tool" data-widget="collapse"><i
                                    class="fa fa-minus"></i>
                            </button>
                            <button type="button" class="btn btn-box-tool" data-widget="remove"><i
                                    class="fa fa-times"></i></button>
                        </div>
                    </div>
                    <!-- /.box-header -->
                    <div class="box-body">
                        <div class="table-responsive">
                            <table class="table no-margin">
                                <thead>
                                <tr>
                                    <th>Visite ID</th>
                                    <th>Nom du client</th>
                                    <th>Avis du client</th>
                                </tr>
                                </thead>
                                <tbody>
                                <%
                                    LinkedList<Rapport> rapports = new RapportDAO().getByAgent(loggedAgent.getId());
                                    if (rapports.size() <= 5) {
                                        for (Rapport rapport : rapports) {
                                            String avis;
                                            String color;
                                            if (rapport.getEtatClient() == EtatClient.ABSENT) {
                                                avis = "Absent";
                                                color = "label-danger";
                                            } else {
                                                color = rapport.isAvis() ? "label-success" : "label-warning";
                                                avis = rapport.isAvis() ? "Positif" : "Négatif";
                                            }
                                            out.print("" +
                                                    "<tr>\n" +
                                                    "<td><a href=\"pages/examples/invoice.html\">" + rapport.getVisite().getId() + "</a></td>\n" +
                                                    "<td><a href=\"pages/examples/invoice.html\">" + rapport.getVisite().getClient().getNom() + " " + rapport.getVisite().getClient().getPrenom() + "</a></td>\n" +
                                                    "<td><span class=\"label " + color + "\">" + avis + "</span></td>\n" +
                                                    "</tr>");
                                        }
                                    } else
                                        for (int i = 1; i < 5; i++) {
                                            String avis;
                                            String color;
                                            if (rapports.get(i).getEtatClient() == EtatClient.ABSENT) {
                                                avis = "Absent";
                                                color = "label-danger";
                                            } else {
                                                color = rapports.get(i).isAvis() ? "label-success" : "label-warning";
                                                avis = rapports.get(i).isAvis() ? "Positif" : "Négatif";
                                            }
                                            out.print("" +
                                                    "<tr>\n" +
                                                    "<td><a href=\"pages/examples/invoice.html\">" + rapports.get(i).getVisite().getId() + "</a></td>\n" +
                                                    "<td><a href=\"pages/examples/invoice.html\">" + rapports.get(i).getVisite().getClient().getNom() + " " + rapports.get(i).getVisite().getClient().getPrenom() + "</a></td>\n" +
                                                    "<td><span class=\"label " + color + ">" + avis + "</span></td>\n" +
                                                    "</tr>");
                                        }

                                %>
                                </tbody>
                            </table>
                        </div>
                        <!-- /.table-responsive -->
                    </div>
                    <!-- /.box-body -->

                    <!-- /.box-footer -->
                </div>
                <!-- /.box -->
            </div>
            <!-- /.col -->

            <div class="col-md-4">
                <div class="box box-default">
                    <div class="box-header with-border">
                        <h3 class="box-title">Ventes par region</h3>

                        <div class="box-tools pull-right">
                            <button type="button" class="btn btn-box-tool" data-widget="collapse"><i
                                    class="fa fa-minus"></i>
                            </button>
                            <button type="button" class="btn btn-box-tool" data-widget="remove"><i
                                    class="fa fa-times"></i></button>
                        </div>
                    </div>
                    <!-- /.box-header -->
                    <div class="box-body">
                        <div class="row">
                            <div class="col-md-8">
                                <div class="chart-responsive">
                                    <canvas id="pieChart" height="150"></canvas>
                                </div>
                                <!-- ./chart-responsive -->
                            </div>
                            <!-- /.col -->
                            <div class="col-md-4">
                                <ul class="chart-legend clearfix">
                                    <%!
                                        LinkedList<Localite> localites = new VisitesDao().getTopFiveRegions();
                                    %>

                                    <li>
                                        <i class="fa fa-circle-o text-red"></i> <%out.print(localites.get(0).getNom());%>
                                    </li>
                                    <li>
                                        <i class="fa fa-circle-o text-green"></i><%out.print(localites.get(1).getNom());%>
                                    </li>
                                    <li>
                                        <i class="fa fa-circle-o text-yellow"></i> <%out.print(localites.get(2).getNom());%>
                                    </li>
                                    <li>
                                        <i class="fa fa-circle-o text-aqua"></i> <%out.print(localites.get(3).getNom());%>
                                    </li>
                                    <li>
                                        <i class="fa fa-circle-o text-light-blue"></i> <%out.print(localites.get(4).getNom());%>
                                    </li>
                                    <li><i class="fa fa-circle-o text-gray"></i>Autres</li>
                                </ul>
                            </div>
                            <!-- /.col -->
                        </div>
                        <!-- /.row -->
                    </div>
                    <!-- /.box-body -->
                </div>
                <!-- /.box -->
            </div>
            <!-- /.col -->
        </div>
        <!-- /.row -->
    </section>
    <!-- /.content -->
    <!-- /.content-wrapper -->
</div>
<!-- ./wrapper -->
<%!private VisitesStats visitesStats = new VisitesStats();%>

<script>
    $(function () {
        'use strict';
        /* ChartJS
     * -------
     * Here we will create a few charts using ChartJS
     */

        // -----------------------
        // - MONTHLY SALES CHART -
        // -----------------------

        // Get context with jQuery - using jQuery's .get() method.
        var salesChartCanvas = $('#salesChart').get(0).getContext('2d');
        // This will get the first returned node in the jQuery collection.
        var salesChart = new Chart(salesChartCanvas);

        var salesChartData = {
            labels: ['Janvier', 'Fevrier', 'Mars', 'Avril', 'Mai', 'Juin', 'Juillet', 'Aout', 'Septembre', 'Octobre', 'Novembre', 'Decembre'],
            datasets: [
                {
                    label: 'Digital Goods',
                    fillColor: 'rgba(60,141,188,0.9)',
                    strokeColor: 'rgba(60,141,188,0.8)',
                    pointColor: '#3b8bba',
                    pointStrokeColor: 'rgba(60,141,188,1)',
                    pointHighlightFill: '#fff',
                    pointHighlightStroke: 'rgba(60,141,188,1)',
                    data: [
                        <%out.print(visitesStats.allVisitesByMonth(Month.JANUARY));%>,
                        <%out.print(visitesStats.allVisitesByMonth(Month.FEBRUARY));%>,
                        <%out.print(visitesStats.allVisitesByMonth(Month.MARCH));%>,
                        <%out.print(visitesStats.allVisitesByMonth(Month.APRIL));%>,
                        <%out.print(visitesStats.allVisitesByMonth(Month.MAY));%>,
                        <%out.print(visitesStats.allVisitesByMonth(Month.JUNE));%>,
                        <%out.print(visitesStats.allVisitesByMonth(Month.JULY));%>,
                        <%out.print(visitesStats.allVisitesByMonth(Month.AUGUST));%>,
                        <%out.print(visitesStats.allVisitesByMonth(Month.SEPTEMBER));%>,
                        <%out.print(visitesStats.allVisitesByMonth(Month.OCTOBER));%>,
                        <%out.print(visitesStats.allVisitesByMonth(Month.NOVEMBER));%>,
                        <%out.print(visitesStats.allVisitesByMonth(Month.DECEMBER));%>
                    ]
                }
            ]
        };

        var salesChartOptions = {
            // Boolean - If we should show the scale at all
            showScale: true,
            // Boolean - Whether grid lines are shown across the chart
            scaleShowGridLines: false,
            // String - Colour of the grid lines
            scaleGridLineColor: 'rgba(0,0,0,.05)',
            // Number - Width of the grid lines
            scaleGridLineWidth: 1,
            // Boolean - Whether to show horizontal lines (except X axis)
            scaleShowHorizontalLines: true,
            // Boolean - Whether to show vertical lines (except Y axis)
            scaleShowVerticalLines: true,
            // Boolean - Whether the line is curved between points
            bezierCurve: true,
            // Number - Tension of the bezier curve between points
            bezierCurveTension: 0.3,
            // Boolean - Whether to show a dot for each point
            pointDot: false,
            // Number - Radius of each point dot in pixels
            pointDotRadius: 4,
            // Number - Pixel width of point dot stroke
            pointDotStrokeWidth: 1,
            // Number - amount extra to add to the radius to cater for hit detection outside the drawn point
            pointHitDetectionRadius: 20,
            // Boolean - Whether to show a stroke for datasets
            datasetStroke: true,
            // Number - Pixel width of dataset stroke
            datasetStrokeWidth: 2,
            // Boolean - Whether to fill the dataset with a color
            datasetFill: true,
            // String - A legend template
            <%--legendTemplate: "<ul class=\'<%=name.toLowerCase()%>-legend\'><% for (var i=0; i<datasets.length; i++){%><li><span style=\'background-color:<%=datasets[i].lineColor%>\'></span><%=datasets[i].label%></li><%}%></ul>",--%>
            // Boolean - whether to maintain the starting aspect ratio or not when responsive, if set to false, will take up entire container
            maintainAspectRatio: true,
            // Boolean - whether to make the chart responsive to window resizing
            responsive: true
        };

        // Create the line chart
        salesChart.Line(salesChartData, salesChartOptions);
    });
</script>
<!-- jQuery 3 -->
<script src="bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- FastClick -->
<script src="bower_components/fastclick/lib/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="dist/js/adminlte.min.js"></script>
<!-- Sparkline -->
<script src="bower_components/jquery-sparkline/dist/jquery.sparkline.min.js"></script>
<!-- SlimScroll -->
<script src="bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<!-- ChartJS -->
<script src="bower_components/chart.js/Chart.js"></script>
<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
<!-- AdminLTE for demo purposes -->
<script src="dist/js/demo.js"></script>
</body>

</html>