<%@ page import="control.servlets.MyServlet" %>
<%@ page import="control.statistics.globales.LogementsStats" %>
<%@ page import="model.beans.Localite" %>
<%@ page import="model.beans.humans.Employe" %>
<%@ page import="model.db.daos.LocaliteDAO" %>
<%@ page import="java.time.Month" %>
<%@ page import="java.util.LinkedList" %>
<!DOCTYPE html>
<html>
<% Employe loggedAgent = (Employe) request.getSession().getAttribute(MyServlet.LOGGED_IN_USER);%>
<%!
    private LogementsStats logementsStats = new LogementsStats();

%>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>AdminLTE 2 | Dashboard</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.7 -->
    <link rel="stylesheet" href="../bower_components/bootstrap/dist/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="../bower_components/font-awesome/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="../bower_components/Ionicons/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="../dist/css/AdminLTE.min.css">
    <!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="../dist/css/skins/_all-skins.min.css">

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
            <div class="col-md-3 ">
                <div class="info-box">
                    <span class="info-box-icon bg-aqua"><i class="glyphicon glyphicon-home"></i></span>

                    <div class="info-box-content">
                        <span class="info-box-text">Tout les logements</span>
                        <span class="info-box-number"><%out.print(logementsStats.logementsNbr());%></span>
                    </div>
                    <!-- /.info-box-content -->
                </div>
                <!-- /.info-box -->
            </div>
            <!-- /.col -->
            <div class="col-md-3 ">
                <div class="info-box">
                    <span class="info-box-icon bg-red"><i class="glyphicon glyphicon-eye-close"></i></span>

                    <div class="info-box-content">
                        <span class="info-box-text">Logements gelés</span>
                        <span class="info-box-number"><%out.print(logementsStats.logementsGeleNbr());%></span>
                    </div>
                    <!-- /.info-box-content -->
                </div>
                <!-- /.info-box -->
            </div>
            <!-- /.col -->

            <!-- fix for small devices only -->
            <div class="clearfix visible-sm-block"></div>

            <div class="col-md-3 ">
                <div class="info-box">
                    <span class="info-box-icon bg-green"><i class="fa fa-credit-card"></i></span>

                    <div class="info-box-content">
                        <span class="info-box-text">logements vendus</span>
                        <span class="info-box-number"><%out.print(logementsStats.logementsVendusNbr());%></span>
                    </div>
                    <!-- /.info-box-content -->
                </div>
                <!-- /.info-box -->
            </div>
            <!-- /.col -->
            <!-- /.col -->
            <div class="col-md-3 ">
                <div class="info-box">
                    <span class="info-box-icon bg-yellow"><i class="glyphicon glyphicon-globe"></i></span>

                    <div class="info-box-content">
                        <span class="info-box-text">Regions</span>
                        <span class="info-box-number">13</span>
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
                        <h3 class="box-title">Nombre des logements vendus (Bleu) et a vendre (Vert)</h3>

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
                            <div class="col-md-12">
                                <div class="chart">
                                    <!-- Sales Chart Canvas -->
                                    <canvas id="logements" style="height: 300px;"></canvas>
                                </div>
                                <!-- /.chart-responsive -->
                            </div>
                        </div>
                        <!-- /.col -->
                        <!-- /.row -->
                    </div>
                    <!-- ./box-body -->
                </div>
                <!-- /.box -->
                <!-- /.col -->
            </div>
            <div class="col-md-8">
                <div class="box">
                    <div class="box-header with-border">
                        <h3 class="box-title">Nombre de logements par region</h3>

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
                            <div class="col-md-12">
                                <div class="chart">
                                    <!-- Sales Chart Canvas -->
                                    <canvas id="logementsPerRegion" style="height: 300px;"></canvas>
                                </div>
                                <!-- /.chart-responsive -->
                            </div>
                        </div>
                        <!-- /.col -->
                        <!-- /.row -->
                    </div>
                    <!-- ./box-body -->
                </div>
                <!-- /.box -->
                <!-- /.col -->
            </div>

            <div class="col-md-4">

                <div class="box box-default">
                    <div class="box-header with-border">
                        <h3 class="box-title">Etat des logements</h3>

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
                            <div class="chart-responsive" style="height: 300px;">
                                <canvas id="logRepart"></canvas>
                            </div>
                            <!-- ./chart-responsive -->
                            <!-- /.col -->

                            <!-- /.col -->
                        </div>
                        <!-- /.row -->
                    </div>
                    <!-- /.box-body -->
                </div>                            <!-- /.col -->
            </div>

            <!-- Main row -->


            <!-- /.content -->
            <!-- /.content-wrapper -->
        </div>
        <!-- ./wrapper -->

        <!-- jQuery 3 -->
        <script src="../bower_components/jquery/dist/jquery.min.js"></script>
        <!-- Bootstrap 3.3.7 -->
        <script src="../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
        <!-- FastClick -->
        <script src="../bower_components/fastclick/lib/fastclick.js"></script>
        <!-- AdminLTE App -->
        <script src="../dist/js/adminlte.min.js"></script>
        <!-- Sparkline -->
        <script src="../bower_components/jquery-sparkline/dist/jquery.sparkline.min.js"></script>
        <!-- SlimScroll -->
        <script src="../bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
        <!-- ChartJS -->
        <script src="../bower_components/chart.js/Chart.js"></script>
        <!-- AdminLTE for demo purposes -->
        <script src="../dist/js/demo.js"></script>
    </section>
</div>


</body>
<script>

    // /* ChartJS
    //  * -------
    //  * Here we will create a few charts using ChartJS
    //  */
    //
    // // -----------------------
    // // - MONTHLY SALES CHART -
    // // -----------------------
    //
    // // Get context with jQuery - using jQuery's .get() method.
    var salesChartCanvas = $('#logements').get(0).getContext('2d');
    // // This will get the first returned node in the jQuery collection.

    var salesChartData = {
        labels: ['Janvier', 'Fevrier', 'Mars', 'Avril', 'Mai', 'Juin', 'Juillet', 'Aout', 'Septembre', 'Octobre', 'Novembre', 'Decembre'],
        datasets: [
            {
                label: 'Tout les logements',
                backgroundColor: '#3c8dbc',
                data: [
                    <%out.print(logementsStats.logementsAVendre());%>,
                    <%out.print(logementsStats.logementsAVendre());%>,
                    <%out.print(logementsStats.logementsAVendre());%>,
                    <%out.print(logementsStats.logementsAVendre());%>,
                    <%out.print(logementsStats.logementsAVendre());%>,
                    <%out.print(logementsStats.logementsAVendre());%>,
                    <%out.print(logementsStats.logementsAVendre());%>,
                    <%out.print(logementsStats.logementsAVendre());%>,
                    <%out.print(logementsStats.logementsAVendre());%>,
                    <%out.print(logementsStats.logementsAVendre());%>,
                    <%out.print(logementsStats.logementsAVendre());%>,
                    <%out.print(logementsStats.logementsAVendre());%>
                ]
            },
            {
                label: 'Logements vendus',
                backgroundColor: '#00a65a',
                data: [
                    <%out.print(logementsStats.logementsVendusNbrVariation().get(Month.JANUARY));%>,
                    <%out.print(logementsStats.logementsVendusNbrVariation().get(Month.FEBRUARY));%>,
                    <%out.print(logementsStats.logementsVendusNbrVariation().get(Month.MARCH));%>,
                    <%out.print(logementsStats.logementsVendusNbrVariation().get(Month.APRIL));%>,
                    <%out.print(logementsStats.logementsVendusNbrVariation().get(Month.MAY));%>,
                    <%out.print(logementsStats.logementsVendusNbrVariation().get(Month.JUNE));%>,
                    <%out.print(logementsStats.logementsVendusNbrVariation().get(Month.JULY));%>,
                    <%out.print(logementsStats.logementsVendusNbrVariation().get(Month.AUGUST));%>,
                    <%out.print(logementsStats.logementsVendusNbrVariation().get(Month.SEPTEMBER));%>,
                    <%out.print(logementsStats.logementsVendusNbrVariation().get(Month.OCTOBER));%>,
                    <%out.print(logementsStats.logementsVendusNbrVariation().get(Month.NOVEMBER));%>,
                    <%out.print(logementsStats.logementsVendusNbrVariation().get(Month.DECEMBER));%>
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
        // Boolean - whether to maintain the starting aspect ratio or not when responsive, if set to false, will take up entire container
        maintainAspectRatio: true,
        // Boolean - whether to make the chart responsive to window resizing
        responsive: true
    };

    // Create the  chart
    var salesChart = new Chart(salesChartCanvas, {
        type: 'line',
        data: salesChartData,
        options: salesChartOptions
    });

    // ---------------------------
    // - END MONTHLY SALES CHART -
    // ---------------------------

</script>
<script>

    // /* ChartJS
    //  * -------
    //  * Here we will create a few charts using ChartJS
    //  */
    //
    // // -----------------------
    // // - MONTHLY SALES CHART -
    // // -----------------------
    //
    // // Get context with jQuery - using jQuery's .get() method.
    var salesChartCanvas = $('#logementsPerRegion').get(0).getContext('2d');
    // // This will get the first returned node in the jQuery collection.

    var salesChartData = {
        labels: [
            <%
                LinkedList<Localite> localites = new LocaliteDAO().getAll();
                for (Localite localite : localites){
                    out.print("'"+localite.getNom()+"',");
                }
            %>
        ],
        datasets: [
            {
                label: 'Logements',
                backgroundColor: '#3B8BBA',
                data: [
                    <%
                        for (Localite localite : localites){
                            out.print(logementsStats.nbrLogementsPerRegion(localite.getId())+",");
                        }
                    %>
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
        // Boolean - whether to maintain the starting aspect ratio or not when responsive, if set to false, will take up entire container
        maintainAspectRatio: true,
        // Boolean - whether to make the chart responsive to window resizing
        responsive: true
    };

    // Create the  chart
    var salesChart = new Chart(salesChartCanvas, {
        type: 'bar',
        data: salesChartData,
        options: salesChartOptions
    });

    // ---------------------------
    // - END MONTHLY SALES CHART -
    // ---------------------------

</script>

<script>

    // -------------
    // - PIE CHART -
    // -------------
    // Get context with jQuery - using jQuery's .get() method.
    var pieChartCanvas = $('#logRepart').get(0).getContext('2d');
    var PieData =
        {
            datasets: [{
                data: [<%out.print(logementsStats.logementsGeleNbr());%>,
                    <%out.print(logementsStats.logementsVendusNbr());%>,
                    <%out.print(logementsStats.logementsAVendre());%>,
                ],
                backgroundColor: [
                    '#f56954',
                    '#00a65a',
                    '#00c0ef'
                ]
            }],

            labels: [
                'Gelés',
                'Vendus',
                'a vendre'
            ]
        };
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
        maintainAspectRatio: false
    };
    // Create pie or douhnut chart
    // You can switch between pie and douhnut using the method below.
    var pieChart = new Chart(pieChartCanvas, {
        type: 'doughnut',
        data: PieData,
        options: pieOptions
    });

    // -----------------
    // - END PIE CHART -
    // -----------------

</script>
</html>