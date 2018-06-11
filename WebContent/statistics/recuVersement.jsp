<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="model.db.ContactInfosDAO" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="control.servlets.MyServlet" %>
<%@ page import="model.beans.humans.Employe" %>
<%@ page import="model.beans.humans.Client" %>
<%@ page import="model.beans.Versement" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="model.db.daos.VersementDAO" %>
<%@ page import="model.db.daos.ClientDAO" %>
<%@ page import="model.db.daos.VentesDAO" %>
<%@ page import="model.beans.Vente" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title><%out.print(new ContactInfosDAO().getNomSociete());%> | Reçu versement</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.7 -->
    <link rel="stylesheet" href="../../bower_components/bootstrap/dist/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="../../bower_components/font-awesome/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="../../bower_components/Ionicons/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="../../dist/css/AdminLTE.min.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <!-- Google Font -->
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
</head>
<body onload="window.print();">
<div class="wrapper">
    <!-- Main content -->
    <section class="invoice">
        <!-- title row -->
        <div class="row">
            <div class="col-xs-12">
                <h2 class="page-header">
                    <i class="fa fa-globe"></i>SARL <%out.print(new ContactInfosDAO().getNomSociete());%>.
                    <%
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                        LocalDate localDate = LocalDate.now();
                    %>
                    <small class="pull-right">Date: <%out.print(dtf.format(localDate));%></small>
                </h2>
            </div>
            <!-- /.col -->
        </div>
        <!-- info row -->
        <div class="row invoice-info">
            <div class="col-sm-4 invoice-col">
                Par
                <address>
                    <%Employe loggedInEmploye = (Employe) request.getSession().getAttribute(MyServlet.LOGGED_IN_USER);%>
                    <strong><%out.print(loggedInEmploye.getFullName());%>
                        , <%out.print(request.getSession().getAttribute(MyServlet.LOGGED_IN_USER_TYPE));%>.</strong><br>
                    Téléphone: <%out.print(loggedInEmploye.getTel());%><br>
                    Email:  <%out.print(loggedInEmploye.getEmail());%>
                </address>
            </div>
            <!-- /.col -->
            <div class="col-sm-4 invoice-col">
                Pour:
                <%
                    LinkedList<Versement> versements = new LinkedList<>();
                    String byWech = request.getParameter("byWech");
                    Client client = new Client();
                    if (byWech != null) {
                        if (byWech.equals("client")) {
                            int clientId = Integer.parseInt(request.getParameter("versementByClientId"));
                            versements = new VersementDAO().getByClient(clientId);
                            client = (Client) new ClientDAO().getById(clientId);
                        } else {
                            int venteId = Integer.parseInt(request.getParameter("versementByVenteId"));
                            versements = new VersementDAO().getByVente(venteId);
                            client = ((Vente) new VentesDAO().getById(venteId)).getClient();
                        }
                    } else {
                        System.out.println("bywech = null");
                    }
                %>
                <address>
                    <strong><%out.print(client.getFullName());%></strong><br>
                    <%out.print(client.getAdresse());%><br>
                    Téléphone: <%out.print(client.getTel());%><br>
                    Email: <%out.print(client.getEmail());%>
                </address>
            </div>
            <!-- /.col -->
            <div class="col-sm-4 invoice-col">
                <table>
                    <tr>
                        <td><b>ID</b></td>
                        <td><b>Montant</b></td>
                        <td><b>Date</b></td>
                    </tr>
                    <%
                        for (Versement versement : versements) {
                            out.print("<tr><td>" + versement.getId() + "</td><td>" + versement.getMontant() + " DA</td><td>" + versement.getDate() + "</td>");
                        }
                    %>
                </table>
                <br>
            </div>
            <!-- /.col -->
        </div>
        <%
            if (request.getParameter("byWech").equals("vente")) {
                out.print("<br><b>Total des versements: </b>" + new VersementDAO().getSommeVersementsByVente(versements.get(0).getVente().getId()) + " DA");
                out.print("<br><b>Reste à payer: </b>" + (versements.get(0).getVente().getLogement().getPrix() - new VersementDAO().getSommeVersementsByVente(versements.get(0).getVente().getId())) + " DA");
            }

        %>
        <!-- /.row -->
    </section>
    <!-- /.content -->
</div>
<!-- ./wrapper -->
</body>
</html>
