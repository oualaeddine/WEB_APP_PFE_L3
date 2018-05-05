<%@ page import="control.statistics.globales.VentesStats" %>
<%@ page import="control.statistics.globales.LogementsStats" %>
<!DOCTYPE html>
<html>
<%
    VentesStats ventesStats = new VentesStats();
    LogementsStats logementsStats1 = new LogementsStats();
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
                <h4>Nombre de ventes total</h4>

                <p><%out.print(ventesStats.ventesNbr());%> ventes.</p>
            </div>
        </div>
        <div class="col-md-3 ">
            <div class="callout callout-success">
                <h4>Nombre de ventes terminées</h4>

                <p><%out.print(ventesStats.confirmedVentesNbr());%> ventes.</p>
            </div>
        </div>
        <div class="col-md-3 ">
            <div class="callout callout-danger">
                <h4>Nombre de ventes annulées</h4>

                <p><%out.print(ventesStats.canceledVentesNbr());%> ventes.</p>
            </div>
        </div>
        <div class="col-md-3 ">
            <div class="callout callout-warning">
                <h4>Nombre de ventes en cours</h4>

                <p><%out.print(ventesStats.pendingVentesNbr());%> ventes.</p>
            </div>
        </div>
        <div class="col-md-3 ">
            <div class="info-box">
                <span class="info-box-icon bg-aqua"><i class="ion "></i></span>

                <div class="info-box-content">
                    <span class="info-box-text">Prix total des logements</span>
                    <span class="info-box-number"><%out.print(logementsStats1.prixTotalDesLogements());%> Da</span>
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
                    <span class="info-box-text">Revenus ventes de cette année</span>
                    <span class="info-box-number"><%out.print(ventesStats.getRevenusAnnuels());%> Da</span>
                </div>
                <!-- /.info-box-content -->
            </div>
            <!-- /.info-box -->
        </div>
        <!-- /.col -->


        <!-- fix for small devices only -->
        <div class="clearfix visible-sm-block"></div>


        <%--<div class="col-md-3 ">--%>
        <%--<div class="info-box">--%>
        <%--<span class="info-box-icon bg-red"><i class="fa "></i></span>--%>

        <%--<div class="info-box-content">--%>
        <%--<span class="info-box-text">Pertes sur annulations</span>--%>
        <%--<span class="info-box-number">6 385 000.00 Da</span>--%>
        <%--</div>--%>
        <%--<!-- /.info-box-content -->--%>
        <%--</div>--%>
        <%--<!-- /.info-box -->--%>
        <%--</div>--%>
        <!-- /.col -->
        <%--<div class="col-md-3 ">--%>
        <%--<div class="info-box">--%>
        <%--<span class="info-box-icon bg-yellow"><i class="ion "></i></span>--%>

        <%--<div class="info-box-content">--%>
        <%--<span class="info-box-text">Revenus estimés</span>--%>
        <%--<span class="info-box-number">49 006 651.00</span>--%>
        <%--</div>--%>
        <%--<!-- /.info-box-content -->--%>
        <%--</div>--%>
        <%--<!-- /.info-box -->--%>
        <%--</div>--%>
        <!-- /.col -->


        <div class="row">
            <div class="col-md-6">
                <div class="box">
                    <div class="box-header with-border">
                        <h3 class="box-title">Variation nombre des ventes terminées ,annulées et en cours </h3>

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
                                    <canvas id="variationVentesPrice" style="height: 300px;"></canvas>
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
            <div class="col-md-6">
                <div class="box">
                    <div class="box-header with-border">
                        <h3 class="box-title">Variation monetaire</h3>

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
                                    <p>somme des ventes terminées,annulées et en cours et le prix total des
                                        logements</p>
                                    <!-- Sales Chart Canvas -->
                                    <canvas id="variationVentesNbr" style="height: 300px;"></canvas>
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
                        <h3 class="box-title">nombre de ventes par region</h3>

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
                                <div class="chart">
                                    <!-- Sales Chart Canvas -->
                                    <canvas id="ventesPerRegion" style="height: 300px;"></canvas>
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
                        <h3 class="box-title">rentabilités des regions</h3>

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
                        <ul class="chart-legend clearfix">
                            <li><i class="fa fa-circle-o text-red"></i> constantine <strong>1 285 000.00da</strong>
                                &nbsp;&nbsp;
                            </li>
                            <li><i class="fa fa-circle-o text-green"></i>ali mendjeli <strong>189 285 000.00da</strong>
                                &nbsp;&nbsp;
                            </li>
                            <li><i class="fa fa-circle-o text-yellow"></i>skikda <strong>89 252 000.00da</strong> &nbsp;
                                &nbsp;
                            </li>
                            <li><i class="fa fa-circle-o text-aqua"></i> brooklyn <strong>89 285 000.00da</strong>
                                &nbsp;&nbsp;
                            </li>
                        </ul>
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
    var salesChartCanvas = $('#variationVentesNbr').get(0).getContext('2d');
    // // This will get the first returned node in the jQuery collection.
    var salesChart = new Chart(salesChartCanvas);

    var salesChartData = {
        labels: ['Janvier', 'Fevrier', 'Mars', 'Avril', 'Mai', 'Juin', 'Juillet', 'Aout', 'Septembre', 'Octobre', 'Novembre', 'Decembre'],
        datasets: [
            {
                label: 'Prix total de Tout les logements',
                fillColor: '#3b8bba',
                strokeColor: '#3b8bba',
                pointColor: '#3b8bba',
                pointStrokeColor: '#3b8bba',
                pointHighlightFill: '#fff',
                pointHighlightStroke: '#3b8bba',
                data: [128, 148, 152, 160, 186, 300, 312, 320, 340, 368, 380, 307]
            },
            {
                label: 'revenus',
                fillColor: '#3cba00',
                strokeColor: '#3cba00',
                pointColor: '#3cba00',
                pointStrokeColor: '#3cba00',
                pointHighlightFill: '#fff',
                pointHighlightStroke: '#3cba00',
                data: [18, 118, 130, 19, 120, 140, 150, 186, 127, 190, 186, 127]
            },
            {
                label: 'pertes annulations',
                fillColor: '#ba1b00',
                strokeColor: '#ba1b00',
                pointColor: '#ba1b00',
                pointStrokeColor: '#ba1b00',
                pointHighlightFill: '#fff',
                pointHighlightStroke: '#ba1b00',
                data: [8, 18, 30, 9, 20, 40, 50, 86, 27, 90, 86, 27]
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
    salesChart.Line(salesChartData, salesChartOptions);

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
    var salesChartCanvas = $('#ventesPerRegion').get(0).getContext('2d');
    // // This will get the first returned node in the jQuery collection.
    var salesChart = new Chart(salesChartCanvas);

    var salesChartData = {
        labels: ['constantine', 'ali mendjlei', 'Mars', 'didouch', 'Mai'],
        datasets: [
            {
                label: 'Tout les logements',
                fillColor: '#3b8bba',
                strokeColor: '#3b8bba',
                pointColor: '#3b8bba',
                pointStrokeColor: '#3b8bba',
                pointHighlightFill: '#fff',
                pointHighlightStroke: '#3b8bba',
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
    var pieChartCanvas = $('#logRepart').get(0).getContext('2d');
    var pieChart = new Chart(pieChartCanvas);
    var PieData = [
        {
            value: 700,
            color: '#f56954',
            highlight: '#f56954',
            label: 'Gelés'
        },
        {
            value: 500,
            color: '#00a65a',
            highlight: '#00a65a',
            label: 'Vendus'
        },
        {
            value: 400,
            color: '#f39c12',
            highlight: '#f39c12',
            label: 'en vente'
        },
        {
            value: 600,
            color: '#00c0ef',
            highlight: '#00c0ef',
            label: 'a vendre'
        }
    ];
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
    pieChart.Doughnut(PieData, pieOptions);
    // -----------------
    // - END PIE CHART -
    // -----------------


</script>


</body>

</html>