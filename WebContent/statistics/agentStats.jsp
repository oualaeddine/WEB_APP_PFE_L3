<%@ page import="model.beans.humans.Employe" %>
<%@ page import="control.servlets.MyServlet" %>
<%@ page import="control.statistics.perso.AgentStats" %>
<%@ page import="model.db.daos.*" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="model.beans.Rapport" %>
<!DOCTYPE html>
<html>
<%Employe loggedAgent = (Employe) request.getSession().getAttribute(MyServlet.LOGGED_IN_USER);%>
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
                                            String avis = rapport.isAvis() ? "Positif" : "Négatif";
                                            out.print("" +
                                                    "<tr>\n" +
                                                    "<td><a href=\"pages/examples/invoice.html\">" + rapport.getId() + "</a></td>\n" +
                                                    "<td><a href=\"pages/examples/invoice.html\">" + rapport.getVisite().getClient().getNom() + " " + rapport.getVisite().getClient().getPrenom() + "</a></td>\n" +
                                                    "<td><span class=\"label label-success\">" + avis + "</span></td>\n" +
                                                    "</tr>");
                                        }
                                    } else
                                        for (int i = 1; i < 5; i++) {
                                            String avis = rapports.get(i).isAvis() ? "Positif" : "Négatif";
                                            out.print("" +
                                                    "<tr>\n" +
                                                    "<td><a href=\"pages/examples/invoice.html\">" + rapports.get(i).getId() + "</a></td>\n" +
                                                    "<td><a href=\"pages/examples/invoice.html\">" + rapports.get(i).getVisite().getClient().getNom() + " " + rapports.get(i).getVisite().getClient().getPrenom() + "</a></td>\n" +
                                                    "<td><span class=\"label label-success\">" + avis + "</span></td>\n" +
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
                                    <li><i class="fa fa-circle-o text-red"></i> Ali mendjeli</li>
                                    <li><i class="fa fa-circle-o text-green"></i>Kheroub</li>
                                    <li><i class="fa fa-circle-o text-yellow"></i> Zouaghi</li>
                                    <li><i class="fa fa-circle-o text-aqua"></i> Brooklyn</li>
                                    <li><i class="fa fa-circle-o text-light-blue"></i> kendahar</li>
                                    <li><i class="fa fa-circle-o text-gray"></i> NYC</li>
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
<script src="dist/js/pages/dashboard2.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="dist/js/demo.js"></script>
</body>

</html>