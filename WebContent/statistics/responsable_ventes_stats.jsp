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
<%@ page import="control.statistics.globales.VentesStats" %>
<%@ page import="control.statistics.globales.LogementsStats" %>
<%@ page import="model.beans.Versement" %>
<%@ page import="control.statistics.globales.ClientsStats" %>
<!DOCTYPE html>
<html>
<% Employe loggedAgent = (Employe) request.getSession().getAttribute(MyServlet.LOGGED_IN_USER);%>
<%
    VisitesStats visitesStats = new VisitesStats();
    AgentStats stats = new AgentStats(loggedAgent.getId());
    VentesStats ventesStats = new VentesStats();
    LogementsStats logementsStats = new LogementsStats();
%>
<%!
    LinkedList<Localite> localites = new VentesDAO().getTopFiveRegions();
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
                        <span class="info-box-text">Total des Logements vendus</span>
                        <span class="info-box-number"><%out.print(logementsStats.logementsVendusNbr());%></span>
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
                        <span class="info-box-text">Total des versements ce mois ci</span>
                        <span class="info-box-number"><%out.print(ventesStats.versementsOfTheMonth());%></span>
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
                        <span class="info-box-text">Total ventes en cours</span>
                        <span class="info-box-number"><%out.print(ventesStats.pendingVentesNbr());%></span>
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
                        <span class="info-box-text">Total des nouveaux client ce mois ci</span>
                        <span class="info-box-number"><%out.print(new ClientsStats().newClientsThisMonthNbr());%></span>
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
                                    <strong>Ventes par mois
                                        : 1
                                        Jan, <%out.print(Calendar.getInstance().get(Calendar.YEAR));%> - 31 Decembre,
                                        <%out.print(Calendar.getInstance().get(Calendar.YEAR));%></strong>
                                </p>

                                <div class="chart">
                                    <!-- Sales Chart Canvas -->
                                    <canvas id="salesChartVentes" style="height: 180px;"></canvas>
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
                                    <span class="progress-text">Nombre de logements vendus:</span>
                                    <span class="progress-number"><b><%out.print(logementsStats.logementsVendusNbr());%></b>/<%out.print(logementsStats.logementsNbr());%></span>
                                    <div class="progress sm">
                                        <div class="progress-bar progress-bar-green"
                                             style="width: <%out.print(logementsStats.logementsVendusPercentage());%>%"></div>
                                    </div>
                                </div>
                                <div class="progress-group">
                                    <span class="progress-text">Nombre des ventes confirmées</span>
                                    <span class="progress-number"><b><%out.print(ventesStats.confirmedVentesNbr());%></b>/<%out.print(ventesStats.ventesNbr());%></span>

                                    <div class="progress sm">
                                        <div class="progress-bar progress-bar-aqua"
                                             style="width: <%out.print(ventesStats.confirmedVentesPercentage());%>%"></div>
                                    </div>
                                </div>
                                <!-- /.progress-group -->
                                <div class="progress-group">
                                    <span class="progress-text">Nombre des ventes annulées </span>
                                    <span class="progress-number"><b><%out.print(ventesStats.canceledVentesNbr());%></b>/<%out.print(ventesStats.ventesNbr());%></span>

                                    <div class="progress sm">
                                        <div class="progress-bar progress-bar-red"
                                             style="width: <%out.print(ventesStats.canceledVentesPercentage());%>%"></div>
                                    </div>
                                </div>

                                <!-- /.progress-group -->
                                <div class="progress-group">
                                    <span class="progress-text">Nombre d'acheteurs</span>
                                    <span class="progress-number"><b><%out.print(ventesStats.acheteursNbr());%></b>/<%out.print(new ClientsStats().clientsNbr());%></span>

                                    <div class="progress sm">
                                        <div class="progress-bar progress-bar-yellow"
                                             style="width: <%out.print(ventesStats.acheteursPercentage());%>%"></div>
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
                        <h3 class="box-title">Derniers versements ajoutés:</h3>

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
                                    <th>Versement ID</th>
                                    <th>Logement ID</th>
                                    <th>Nom du client</th>
                                    <th>Montant du versement</th>
                                </tr>
                                </thead>
                                <tbody>
                                <%
                                    LinkedList<Versement> versements = new VersementDAO().getAll();
                                    if (versements.size() < 5) {
                                        for (Versement versement : versements) {
                                            out.print("" +
                                                    "<tr>\n" +
                                                    "<td>" + versement.getId() + "</td>\n" +
                                                    "<td>" + versement.getVente().getLogement().getId() + "</td>" +
                                                    "<td>" + versement.getVente().getClient().getNom() + " " + versement.getVente().getClient().getPrenom() + "</td>" +
                                                    "<td><span class=\"label  label-success\">" + versement.getMontant() + " DA</span></td>\n" +
                                                    "</tr>");
                                        }
                                    } else
                                        for (int i = 0; i < 5; i++) {
                                            out.print("" +
                                                    "<tr>\n" +
                                                    "<td>" + versements.get(i).getId() + "</td>\n" +
                                                    "<td>" + versements.get(i).getVente().getLogement().getId() + "</td>\n" +
                                                    "<td>" + versements.get(i).getVente().getClient().getNom() + " " + versements.get(i).getVente().getClient().getPrenom() + "</td>\n" +
                                                    "<td><span class=\"label label-success\">" + versements.get(i).getMontant() + " DA</span></td>\n" +
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
                                    <canvas id="pieChart" height="300"></canvas>
                                </div>
                                <!-- ./chart-responsive -->
                            </div>
                            <!-- /.col -->
                            <div class="col-md-4">
                                <ul class="chart-legend clearfix">


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
<script src="./bower_components/chart.js/Chart.js"></script>
<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
<!-- AdminLTE for demo purposes -->
<script src="dist/js/demo.js"></script>
</body>

<script>
    'use strict';
    /* ChartJS
 * -------
 * Here we will create a few charts using ChartJS
 */

    // -----------------------
    // - MONTHLY SALES CHART -
    // -----------------------

    // Get context with jQuery - using jQuery's .get() method.
    var salesChartCanvas = $('#salesChartVentes').get(0).getContext('2d');
    // This will get the first returned node in the jQuery collection.
    var salesChart = new Chart(salesChartCanvas);

    var salesChartData = {
        labels: ['Janvier', 'Fevrier', 'Mars', 'Avril', 'Mai', 'Juin', 'Juillet', 'Aout', 'Septembre', 'Octobre', 'Novembre', 'Decembre'],
        datasets: [
            {
                label: 'Ventes confirmees',
                fillColor: 'rgba(60,141,188,0.9)',
                strokeColor: 'rgba(60,141,188,0.8)',
                pointColor: '#3b8bba',
                pointStrokeColor: 'rgba(60,141,188,1)',
                pointHighlightFill: '#fff',
                pointHighlightStroke: 'rgba(60,141,188,1)',
                data: [
                    <%out.print(ventesStats.confirmedVentesNbrPerMonth(Month.JANUARY));%>,
                    <%out.print(ventesStats.confirmedVentesNbrPerMonth(Month.FEBRUARY));%>,
                    <%out.print(ventesStats.confirmedVentesNbrPerMonth(Month.MARCH));%>,
                    <%out.print(ventesStats.confirmedVentesNbrPerMonth(Month.APRIL));%>,
                    <%out.print(ventesStats.confirmedVentesNbrPerMonth(Month.MAY));%>,
                    <%out.print(ventesStats.confirmedVentesNbrPerMonth(Month.JUNE));%>,
                    <%out.print(ventesStats.confirmedVentesNbrPerMonth(Month.JULY));%>,
                    <%out.print(ventesStats.confirmedVentesNbrPerMonth(Month.AUGUST));%>,
                    <%out.print(ventesStats.confirmedVentesNbrPerMonth(Month.SEPTEMBER));%>,
                    <%out.print(ventesStats.confirmedVentesNbrPerMonth(Month.OCTOBER));%>,
                    <%out.print(ventesStats.confirmedVentesNbrPerMonth(Month.NOVEMBER));%>,
                    <%out.print(ventesStats.confirmedVentesNbrPerMonth(Month.DECEMBER));%>
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

    var salesChart = new Chart(salesChartCanvas, {
        type: 'line',
        data: salesChartData,
        options: salesChartOptions
    });

    // salesChart.Line(salesChartData, salesChartOptions);
</script>
<script>
    var pieChartCanvas = $('#pieChart').get(0).getContext('2d');
    var PieData = {
        datasets: [{
            data: [
                <%out.print(ventesStats.nbrVentesPerRegion(localites.get(0).getId()));%>,
                <%out.print(ventesStats.nbrVentesPerRegion(localites.get(1).getId()));%>,
                <%out.print(ventesStats.nbrVentesPerRegion(localites.get(2).getId()));%>,
                <%out.print(ventesStats.nbrVentesPerRegion(localites.get(3).getId()));%>,
                <%out.print(ventesStats.nbrVentesPerRegion(localites.get(4).getId()));%>
            ],
            backgroundColor: [
                '#f56954',
                '#00a65a',
                '#f39c12',
                '#00c0ef',
                '#3c8dbc'
            ]
        }],
        labels: [
            '<%out.print(localites.get(0).getNom());%>',
            '<%out.print(localites.get(1).getNom());%>',
            '<%out.print(localites.get(2).getNom());%>',
            '<%out.print(localites.get(3).getNom());%>',
            '<%out.print(localites.get(4).getNom());%>'
        ]
    }

    var pieOptions = {
        // Boolean - Whether we should show a stroke on each segment
        segmentShowStroke: true,
        // String - The colour of each segment stroke
        segmentStrokeColor: '#fff',
        // Number - The width of each segment stroke
        segmentStrokeWidth: 1,
        // Number - The percentage of the chart that we cut out of the middle
        percentageInnerCutout: 50, // This is 0 for Pie charts
        // Number - Amount of animation steps
        animationSteps: 100,
        // String - Animation easing effect
        animationEasing: 'easeOutBounce',
        // Boolean - Whether we animate the rotation of the Doughnut
        animateRotate: true,
        // Boolean - Whether we animate scaling the Doughnut from the centre
        animateScale: false,
        // Boolean - whether to make the chart responsive to window resizing
        responsive: true,
        // Boolean - whether to maintain the starting aspect ratio or not when responsive, if set to false, will take up entire container
        maintainAspectRatio: false,
    };
    // Create pie or douhnut chart
    // You can switch between pie and douhnut using the method below.
    var pieChart = new Chart(pieChartCanvas, {
        type: 'doughnut',
        data: PieData,
        options: pieOptions
    });
</script>
</html>