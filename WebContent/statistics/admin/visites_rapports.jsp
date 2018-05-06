<%@ page import="control.statistics.globales.RapportsStats" %>
<%@ page import="control.statistics.globales.VisitesStats" %>
<%@ page import="model.beans.Localite" %>
<%@ page import="model.enums.TypeLogement" %>
<%@ page import="java.time.Month" %>
<%@ page import="java.util.LinkedList" %>
<!DOCTYPE html>
<html>
<%
    VisitesStats visitesStats = new VisitesStats();
    RapportsStats rapportsStats = new RapportsStats();
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
        <div class="col-md-3 ">
            <div class="callout callout-info">
                <h4>Nombre des visites</h4>

                <p><%out.print(visitesStats.visitesNbr());%> visites.</p>
            </div>
        </div>
        <div class="col-md-3 ">
            <div class="callout callout-success">
                <h4>Nombre des visites terminées</h4>

                <p><%out.print(new RapportsStats().rapportsNbr());%> visites.</p>
            </div>
        </div>
        <div class="col-md-3 ">
            <div class="callout callout-danger">
                <h4>Nombre des visites annulées</h4>

                <p><%out.print(visitesStats.visitesAnnuleesNbr());%> visites.</p>
            </div>
        </div>
        <div class="col-md-3 ">
            <div class="callout callout-warning">
                <h4>Nombre des visites prevues</h4>

                <p><%out.print(visitesStats.visitesPrevuesNbr());%> visites.</p>
            </div>
        </div>
        <div class="col-md-3 ">
            <div class="info-box">
                <span class="info-box-icon bg-aqua"><i class="ion "></i></span>

                <div class="info-box-content">
                    <span class="info-box-text">Pourcentage de vente par rapport au nombre de visites  </span>
                    <span class="info-box-number"><%out.print(rapportsStats.positifPercentage());%>%</span>
                </div>
                <!-- /.info-box-content -->
            </div>
            <!-- /.info-box -->
        </div>
        <!-- /.col -->
        <!-- /.row -->
        <div class="col-md-3 ">
            <div class="info-box">
                <span class="info-box-icon bg-green"><i class="ion "></i></span>

                <div class="info-box-content">
                    <span class="info-box-text">Nombre d'avis positifs</span>
                </div>
                <span class="info-box-number"><%out.print(rapportsStats.positifsNbr());%></span>
                <!-- /.info-box-content -->
            </div>
            <!-- /.info-box -->
        </div>
        <!-- /.col -->


        <!-- fix for small devices only -->
        <div class="clearfix visible-sm-block"></div>


        <div class="col-md-3 ">
            <div class="info-box">
                <span class="info-box-icon bg-red"><i class="fa "></i></span>

                <div class="info-box-content">
                    <span class="info-box-text">Nombre d'avis negatifs</span>
                    <span class="info-box-number"><%out.print(rapportsStats.negatifsNbr());%></span>
                </div>
                <!-- /.info-box-content -->
            </div>
            <!-- /.info-box -->
        </div>
        <!-- /.col -->
        <div class="col-md-3 ">
            <div class="info-box">
                <span class="info-box-icon bg-yellow"><i class="ion "></i></span>

                <div class="info-box-content">
                    <span class="info-box-text">Absences des clients</span>
                    <span class="info-box-number"><%out.print(rapportsStats.absencesNbr());%></span>
                </div>
                <!-- /.info-box-content -->
            </div>
            <!-- /.info-box -->
        </div>
        <!-- /.col -->

        <div class="row">
            <div class="col-md-8">
                <div class="box">
                    <div class="box-header with-border">
                        <h3 class="box-title">Nombre de visites</h3>

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
                                    <canvas id="variationVisites" style="height: 300px;"></canvas>
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
                        <h3 class="box-title">5 regions avec le plus grand nombre de visites</h3>

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
                                <canvas id="visitesParRegion"></canvas>
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

            <div class="col-md-12">
                <div class="box">
                    <div class="box-header with-border">
                        <h3 class="box-title">Nombre de visites par type de logement</h3>

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
                                    <canvas id="visitesParTypes" style="height: 300px;"></canvas>
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
        <!-- AdminLTE dashboard demo (This is only for demo purposes) -->
        <!--<script src="../dist/js/pages/dashboard2.js"></script>-->
        <!-- AdminLTE for demo purposes -->
        <script src="../dist/js/demo.js"></script>
    </section>
</div>
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

    $(window).on('load', function () {
        var salesChartCanvas = $('#variationVisites').get(0).getContext('2d');
        // // This will get the first returned node in the jQuery collection.
        var salesChart = new Chart(salesChartCanvas, {
            type: 'line',
            data: {
                labels: ['Janvier', 'Fevrier', 'Mars', 'Avril', 'Mai', 'Juin', 'Juillet', 'Aout', 'Septembre', 'Octobre', 'Novembre', 'Decembre'],
                datasets: [
                    {
                        label: 'visites prevues',
                        backgroundColor: 'rgba(0, 0, 255, 0.2)',
                        data: [
                            <%out.print(visitesStats.nbrVisitesPrevuesForMonth(Month.JANUARY));%>,
                            <%out.print(visitesStats.nbrVisitesPrevuesForMonth(Month.FEBRUARY));%>,
                            <%out.print(visitesStats.nbrVisitesPrevuesForMonth(Month.MARCH));%>,
                            <%out.print(visitesStats.nbrVisitesPrevuesForMonth(Month.APRIL));%>,
                            <%out.print(visitesStats.nbrVisitesPrevuesForMonth(Month.MAY));%>,
                            <%out.print(visitesStats.nbrVisitesPrevuesForMonth(Month.JUNE));%>,
                            <%out.print(visitesStats.nbrVisitesPrevuesForMonth(Month.JULY));%>,
                            <%out.print(visitesStats.nbrVisitesPrevuesForMonth(Month.AUGUST));%>,
                            <%out.print(visitesStats.nbrVisitesPrevuesForMonth(Month.SEPTEMBER));%>,
                            <%out.print(visitesStats.nbrVisitesPrevuesForMonth(Month.OCTOBER));%>,
                            <%out.print(visitesStats.nbrVisitesPrevuesForMonth(Month.NOVEMBER));%>,
                            <%out.print(visitesStats.nbrVisitesPrevuesForMonth(Month.DECEMBER));%>
                        ]
                    },
                    {
                        label: 'visites annulées',
                        backgroundColor: 'rgba(0, 255, 0, 0.2)',
                        data: [
                            <%out.print(visitesStats.nbrVisitesAnnuleesForMonth(Month.JANUARY));%>,
                            <%out.print(visitesStats.nbrVisitesAnnuleesForMonth(Month.FEBRUARY));%>,
                            <%out.print(visitesStats.nbrVisitesAnnuleesForMonth(Month.MARCH));%>,
                            <%out.print(visitesStats.nbrVisitesAnnuleesForMonth(Month.APRIL));%>,
                            <%out.print(visitesStats.nbrVisitesAnnuleesForMonth(Month.MAY));%>,
                            <%out.print(visitesStats.nbrVisitesAnnuleesForMonth(Month.JUNE));%>,
                            <%out.print(visitesStats.nbrVisitesAnnuleesForMonth(Month.JULY));%>,
                            <%out.print(visitesStats.nbrVisitesAnnuleesForMonth(Month.AUGUST));%>,
                            <%out.print(visitesStats.nbrVisitesAnnuleesForMonth(Month.SEPTEMBER));%>,
                            <%out.print(visitesStats.nbrVisitesAnnuleesForMonth(Month.OCTOBER));%>,
                            <%out.print(visitesStats.nbrVisitesAnnuleesForMonth(Month.NOVEMBER));%>,
                            <%out.print(visitesStats.nbrVisitesAnnuleesForMonth(Month.DECEMBER));%>
                        ]
                    },
                    {
                        label: 'visites reportées',
                        backgroundColor: 'rgba(255, 50, 20, 0.2)',
                        data: [
                            <%out.print(visitesStats.nbrVisitesReporteesForMonth(Month.JANUARY));%>,
                            <%out.print(visitesStats.nbrVisitesReporteesForMonth(Month.FEBRUARY));%>,
                            <%out.print(visitesStats.nbrVisitesReporteesForMonth(Month.MARCH));%>,
                            <%out.print(visitesStats.nbrVisitesReporteesForMonth(Month.APRIL));%>,
                            <%out.print(visitesStats.nbrVisitesReporteesForMonth(Month.MAY));%>,
                            <%out.print(visitesStats.nbrVisitesReporteesForMonth(Month.JUNE));%>,
                            <%out.print(visitesStats.nbrVisitesReporteesForMonth(Month.JULY));%>,
                            <%out.print(visitesStats.nbrVisitesReporteesForMonth(Month.AUGUST));%>,
                            <%out.print(visitesStats.nbrVisitesReporteesForMonth(Month.SEPTEMBER));%>,
                            <%out.print(visitesStats.nbrVisitesReporteesForMonth(Month.OCTOBER));%>,
                            <%out.print(visitesStats.nbrVisitesReporteesForMonth(Month.NOVEMBER));%>,
                            <%out.print(visitesStats.nbrVisitesReporteesForMonth(Month.DECEMBER));%>
                        ]
                    }
                ]
            },
            // Boolean - If we should show the scale at all
            options: {
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
                // datasetStroke: true,
                // Number - Pixel width of dataset stroke
                // datasetStrokeWidth: 2,
                // Boolean - Whether to fill the dataset with a color
                // datasetFill: false,
                // String - A legend template
                maintainAspectRatio: true,
                // Boolean - whether to make the chart responsive to window resizing
                responsive: true
            }
        });

        // Create the  chart
        //salesChart.Line(salesChartData, salesChartOptions);

        // ---------------------------
        // - END MONTHLY SALES CHART -
        // ---------------------------
    });
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
    var salesChartCanvas = $('#ventesPerRegion').get(0).getContext('2d');
    // // This will get the first returned node in the jQuery collection.
    var salesChart = new Chart(salesChartCanvas);

    var salesChartData = {
        labels: ['constantine', 'ali mendjlei', 'Mars', 'didouch', 'Mai'],
        datasets: [
            {
                label: 'Tout les logements',

                data: [208, 408, 520, 600, 286]
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
    salesChart.Bar(salesChartData, salesChartOptions);

    // ---------------------------
    // - END MONTHLY SALES CHART -
    // ---------------------------

</script>

<script>

    // -------------
    // - PIE CHART -
    // -------------
    // Get context with jQuery - using jQuery's .get() method.
    var pieChartCanvas = $('#visitesParRegion').get(0).getContext('2d');
    var PieData =
        {
            <%LinkedList<Localite> localites = visitesStats.top5Regions(); %>
            datasets: [{
                data: [
                    <%
                        for(Localite localite : localites){
                            out.print("'"+visitesStats.nbrVisitesPerRegion(localite.getId())+"',");
                        }
                    %>
                ],
                backgroundColor: [
                    '#f56954',
                    '#00a65a',
                    '#f39c12',
                    '#00c0ef',
                    '#a60016'
                ]
            }],

            labels: [
                <%
                    for(Localite localite : localites){
                        out.print("'"+localite.getNom()+"',");
                    }
                %>
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
    var salesChartCanvas = $('#visitesParTypes').get(0).getContext('2d');
    // // This will get the first returned node in the jQuery collection.

    var salesChartData = {
            labels: ['Janvier', 'Fevrier', 'Mars', 'Avril', 'Mai', 'Juin', 'Juillet', 'Aout', 'Septembre', 'Octobre', 'Novembre', 'Decembre'],
            datasets: [
                {
                    label: 'villa',
                    backgroundColor: '#3c8dbc',
                    data: [
                        <%out.print(visitesStats.nbrVisitesParTypeParMois(TypeLogement.VILLA,Month.JANUARY));%>,
                        <%out.print(visitesStats.nbrVisitesParTypeParMois(TypeLogement.VILLA,Month.FEBRUARY));%>,
                        <%out.print(visitesStats.nbrVisitesParTypeParMois(TypeLogement.VILLA,Month.MARCH));%>,
                        <%out.print(visitesStats.nbrVisitesParTypeParMois(TypeLogement.VILLA,Month.APRIL));%>,
                        <%out.print(visitesStats.nbrVisitesParTypeParMois(TypeLogement.VILLA,Month.MAY));%>,
                        <%out.print(visitesStats.nbrVisitesParTypeParMois(TypeLogement.VILLA,Month.JUNE));%>,
                        <%out.print(visitesStats.nbrVisitesParTypeParMois(TypeLogement.VILLA,Month.JULY));%>,
                        <%out.print(visitesStats.nbrVisitesParTypeParMois(TypeLogement.VILLA,Month.AUGUST));%>,
                        <%out.print(visitesStats.nbrVisitesParTypeParMois(TypeLogement.VILLA,Month.SEPTEMBER));%>,
                        <%out.print(visitesStats.nbrVisitesParTypeParMois(TypeLogement.VILLA,Month.OCTOBER));%>,
                        <%out.print(visitesStats.nbrVisitesParTypeParMois(TypeLogement.VILLA,Month.NOVEMBER));%>,
                        <%out.print(visitesStats.nbrVisitesParTypeParMois(TypeLogement.VILLA,Month.DECEMBER));%>
                    ]
                },
                {
                    label: 'appartement',
                    backgroundColor: '#00a65a',
                    data: [
                        <%out.print(visitesStats.nbrVisitesParTypeParMois(TypeLogement.APPARTEMENT,Month.JANUARY));%>,
                        <%out.print(visitesStats.nbrVisitesParTypeParMois(TypeLogement.APPARTEMENT,Month.FEBRUARY));%>,
                        <%out.print(visitesStats.nbrVisitesParTypeParMois(TypeLogement.APPARTEMENT,Month.MARCH));%>,
                        <%out.print(visitesStats.nbrVisitesParTypeParMois(TypeLogement.APPARTEMENT,Month.APRIL));%>,
                        <%out.print(visitesStats.nbrVisitesParTypeParMois(TypeLogement.APPARTEMENT,Month.MAY));%>,
                        <%out.print(visitesStats.nbrVisitesParTypeParMois(TypeLogement.APPARTEMENT,Month.JUNE));%>,
                        <%out.print(visitesStats.nbrVisitesParTypeParMois(TypeLogement.APPARTEMENT,Month.JULY));%>,
                        <%out.print(visitesStats.nbrVisitesParTypeParMois(TypeLogement.APPARTEMENT,Month.AUGUST));%>,
                        <%out.print(visitesStats.nbrVisitesParTypeParMois(TypeLogement.APPARTEMENT,Month.SEPTEMBER));%>,
                        <%out.print(visitesStats.nbrVisitesParTypeParMois(TypeLogement.APPARTEMENT,Month.OCTOBER));%>,
                        <%out.print(visitesStats.nbrVisitesParTypeParMois(TypeLogement.APPARTEMENT,Month.NOVEMBER));%>,
                        <%out.print(visitesStats.nbrVisitesParTypeParMois(TypeLogement.APPARTEMENT,Month.DECEMBER));%>
                    ]
                }

            ]
        }
    ;

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
        type: 'bar',
        data: salesChartData,
        options: salesChartOptions
    });

    // ---------------------------
    // - END MONTHLY SALES CHART -
    // ---------------------------

</script>


</body>

</html>