<%@ page import="control.statistics.globales.LogementsStats" %>
<%@ page import="control.statistics.globales.VentesStats" %>
<%@ page import="java.time.Month" %>
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
                <h4>ventes total</h4>

                <p><%out.print(ventesStats.ventesNbr());%> ventes.</p>
            </div>
        </div>
        <div class="col-md-3 ">
            <div class="callout callout-success">
                <h4>ventes terminées</h4>

                <p><%out.print(ventesStats.confirmedVentesNbr());%> ventes.</p>
            </div>
        </div>
        <div class="col-md-3 ">
            <div class="callout callout-danger">
                <h4>ventes annulées</h4>

                <p><%out.print(ventesStats.canceledVentesNbr());%> ventes.</p>
            </div>
        </div>
        <div class="col-md-3 ">
            <div class="callout callout-warning">
                <h4>ventes en cours</h4>

                <p><%out.print(ventesStats.pendingVentesNbr());%> ventes.</p>
            </div>
        </div>
        <div class="col-md-3 ">
            <div class="info-box">
                <span class="info-box-icon bg-aqua"><i class="fa fa-credit-card"></i></span>

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
                <span class="info-box-icon bg-green"><i class="fa  fa-check"></i></span>

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


        <div class="col-md-3 ">
            <div class="info-box">
                <span class="info-box-icon bg-red"><i class="fa "></i></span>

                <div class="info-box-content">
                    <span class="info-box-text">Pourcentage d'acheteurs</span>
                    <span class="info-box-number"><%out.print(ventesStats.acheteursPercentage());%>%</span>
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
                    <span class="info-box-text">Revenus estimés</span>
                    <span class="info-box-number"><%out.print(ventesStats.revenusEstimes());%> DA</span>
                </div>
                <!-- /.info-box-content -->
            </div>
            <!-- /.info-box -->
        </div>
        <!-- /.col -->


        <div class="row">
            <div class="col-md-6">
                <div class="box">
                    <div class="box-header with-border">
                        <h3 class="box-title">Variation nombre des ventes terminées</h3>

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
    var salesChartCanvas = $('#variationVentesPrice').get(0).getContext('2d');
    // // This will get the first returned node in the jQuery collection.
    var salesChart = new Chart(salesChartCanvas, {
        type: 'line',
        data: {
            labels: ['Janvier', 'Fevrier', 'Mars', 'Avril', 'Mai', 'Juin', 'Juillet', 'Aout', 'Septembre', 'Octobre', 'Novembre', 'Decembre'],
            datasets: [

                {
                    label: 'Ventes terminées',
                    fill: true,
                    backgroundColor: '#9c0200',
                    borderColor: '#ba0200',
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
                        <%out.print(ventesStats.confirmedVentesNbrPerMonth(Month.DECEMBER));%>,
                    ]
                }
            ]
        },
        options: {
            responsive: true,
            title: {
                display: true,
                text: 'Chart.js Line Chart'
            },
            tooltips: {
                mode: 'index',
                intersect: false,
            },
            hover: {
                mode: 'nearest',
                intersect: true
            },
            scales: {
                xAxes: [{
                    display: true,
                    scaleLabel: {
                        display: true,
                        labelString: 'Month'
                    }
                }],
                yAxes: [{
                    display: true,
                    scaleLabel: {
                        display: true,
                        labelString: 'Value'
                    }
                }]
            }
        }
    });

    // Create the  chart
    // salesChart(salesChartData, salesChartOptions);

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
    var salesChartCanvas = $('#variationVentesNbr').get(0).getContext('2d');
    // // This will get the first returned node in the jQuery collection.
    var salesChart = new Chart(salesChartCanvas, {
        type: 'line',
        data: {
            labels: ['Janvier', 'Fevrier', 'Mars', 'Avril', 'Mai', 'Juin', 'Juillet', 'Aout', 'Septembre', 'Octobre', 'Novembre', 'Decembre'],
            datasets: [

                {
                    label: 'Revenus mensuel',
                    fill: true,
                    backgroundColor: '#9c0200',
                    borderColor: '#ba0200',
                    data: [
                        <%out.print(ventesStats.revenusPerMonth(Month.JANUARY));%>,
                        <%out.print(ventesStats.revenusPerMonth(Month.FEBRUARY));%>,
                        <%out.print(ventesStats.revenusPerMonth(Month.MARCH));%>,
                        <%out.print(ventesStats.revenusPerMonth(Month.APRIL));%>,
                        <%out.print(ventesStats.revenusPerMonth(Month.MAY));%>,
                        <%out.print(ventesStats.revenusPerMonth(Month.JUNE));%>,
                        <%out.print(ventesStats.revenusPerMonth(Month.JULY));%>,
                        <%out.print(ventesStats.revenusPerMonth(Month.AUGUST));%>,
                        <%out.print(ventesStats.revenusPerMonth(Month.SEPTEMBER));%>,
                        <%out.print(ventesStats.revenusPerMonth(Month.OCTOBER));%>,
                        <%out.print(ventesStats.revenusPerMonth(Month.NOVEMBER));%>,
                        <%out.print(ventesStats.revenusPerMonth(Month.DECEMBER));%>
                    ]
                }
            ]
        },
        options: {
            responsive: true,
            title: {
                display: true,
                text: 'Chart.js Line Chart'
            },
            tooltips: {
                mode: 'index',
                intersect: false,
            },
            hover: {
                mode: 'nearest',
                intersect: true
            },
            scales: {
                xAxes: [{
                    display: true,
                    scaleLabel: {
                        display: true,
                        labelString: 'Month'
                    }
                }],
                yAxes: [{
                    display: true,
                    scaleLabel: {
                        display: true,
                        labelString: 'Value'
                    }
                }]
            }
        }
    });

    // Create the  chart
    // salesChart(salesChartData, salesChartOptions);

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
    var salesChart = new Chart(salesChartCanvas, {
        type: 'bar',
        data: {
            labels: ['constantine', 'ali mendjlei', 'Mars', 'didouch', 'Mai'],
            datasets: [
                {
                    label: 'ventes terminées',
                    backgroundColor: '#00ba24',
                    data: [208, 408, 520, 600, 286]
                },
                {
                    label: 'ventes annulées',
                    backgroundColor: '#ba0200',
                    data: [300, 6, 128, 200, 113]
                }
            ]
        }
        ,
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
        <%--legendTemplate: '<ul class=\'<%=name.toLowerCase()%>-legend\'><% for (var i=0; i<datasets.length; i++){%><li><span style=\'background-color:<%=datasets[i].lineColor%>\'></span><%=datasets[i].label%></li><%}%></ul>',--%>
        // Boolean - whether to maintain the starting aspect ratio or not when responsive, if set to false, will take up entire container
        maintainAspectRatio: true,
        // Boolean - whether to make the chart responsive to window resizing
        responsive: true
    });

    // Create the  chart
    //salesChart.Bar(salesChartData, salesChartOptions);

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
    var pieChart = new Chart(pieChartCanvas,
        {
            type: 'doughnut',
            data: {
                datasets: [{
                    data: [10, 20, 30],
                    backgroundColor: [
                        '#ff6384', '#36a2eb', '#cc65fe'
                    ]
                }],

                labels: [
                    'Red',
                    'Yellow',
                    'Blue'
                ]
            },
            options: {
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
                // String - A legend template
                <%--legendTemplate: '<ul class=\'<%=name.toLowerCase()%>-legend\'><% for (var i=0; i<segments.length; i++){%><li><span style=\'background-color:<%=segments[i].fillColor%>\'></span><%if(segments[i].label){%><%=segments[i].label%><%}%></li><%}%></ul>',--%>
                // String - A tooltip template
                <%--tooltipTemplate: '<%=value %>  <%=label%> '--%>
            }
        });
    // Create pie or douhnut chart
    // You can switch between pie and douhnut using the method below.
    //   pieChart.Doughnut(PieData, pieOptions);


    // -----------------
    // - END PIE CHART -
    // -----------------


</script>
</body>

</html>