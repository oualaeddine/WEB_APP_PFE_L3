<%@ page import="control.servlets.MyServlet" %>
<%@ page import="model.beans.Localite" %>
<%@ page import="model.beans.humans.Employe" %>
<%@ page import="model.beans.views.TablesView" %>
<%@ page import="model.db.daos.LocaliteDAO" %>
<%@ page import="model.enums.UserType" %>
<%@ page import="java.util.LinkedList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%! private TablesView tablesView = new TablesView(); %>
<%
    UserType userType = (UserType) request.getSession().getAttribute(MyServlet.LOGGED_IN_USER_TYPE);
    Employe employe = (Employe) request.getSession().getAttribute(MyServlet.LOGGED_IN_USER);
    int userId = (int) request.getSession().getAttribute(MyServlet.LOGGED_IN_USER_ID);
    String currentPage = request.getParameter("page");
    tablesView.setLoggedInUserId(userId);
    tablesView.setLoggedInUserType(userType);
%>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <link rel="apple-touch-icon" sizes="76x76" href="./programmerVisite/assets/img/apple-icon.png"/>
    <link rel="icon" type="image/png" href="./programmerVisite/assets/img/favicon.png"/>
    <title>HCH - immobilier </title>

    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport'/>
    <meta name="viewport" content="width=device-width"/>

    <!-- CSS Files -->
    <link href="./programmerVisite/assets/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="./programmerVisite/assets/css/paper-bootstrap-wizard.css" rel="stylesheet"/>
    <link href="./programmerVisite/assets/css/bootstrap-slider.css" rel="stylesheet"/>
    <link href="./programmerVisite/assets/css/datatables-buttons.css" rel="stylesheet"/>

    <!-- CSS Just for demo purpose, don't include it in your project -->
    <!--<link href="./programmerVisite/assets/css/demo.css" rel="stylesheet"/>-->

    <!-- Fonts and Icons -->
    <link href="http://netdna.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.css" rel="stylesheet">
    <link href='https://fonts.googleapis.com/css?family=Muli:400,300' rel='stylesheet' type='text/css'>
    <link href="./programmerVisite/assets/css/themify-icons.css" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.10/css/all.css"
          integrity="sha384-+d0P83n9kaQMCwj8F4RJB66tzIwOKmrdb46+porD/OvrJ+37WqIM7UoBtwHO6Nlg" crossorigin="anonymous">


    <style>

        .slider-handle {
            background: #68B3C8;
        }

        .slider-selection {
            background: #68B3C8;
        }
    </style>

    <!-- fullCalendar -->
    <link rel="stylesheet" href="./programmerVisite/assets/fullcalendar/dist/fullcalendar.min.css">
    <link rel="stylesheet" href="./programmerVisite/assets/fullcalendar/dist/fullcalendar.print.min.css" media="print">
    <!-- DataTables -->
    <link rel="stylesheet" href="./programmerVisite/assets/datatables.net-bs/css/dataTables.bootstrap.min.css">
    <link rel="stylesheet" href="./programmerVisite/assets/datatables-select/select.dataTables.min.css">
    <link rel="stylesheet" href="./programmerVisite/assets/datatables-responsive/responsive.dataTables.min.css">
    <!--<link rel="stylesheet" href="https://bootswatch.com/4/minty/bootstrap.min.css">-->
    <style>

        #top,
        #calendar.fc-unthemed {
            font-family: "Lucida Grande", Helvetica, Arial, Verdana, sans-serif;
        }

        #calendar {
            max-width: 900px;
            margin: 40px auto;
            padding: 0 10px;
        }

        #calendar .fc-bg {
            /*background: #e0f7fa;*/
        }

        #calendar .fc-bgevent {
            background: red;
        }

        #calendar .fc-highlight {
            background: #009688;
        }
    </style>


</head>

<body>
<div class="image-container set-full-height" style="background:#cfd8dc">

    <!--   Big container   -->
    <div class="container">
        <div class="row">
            <div class="col-sm-8 col-sm-offset-2">

                <!--      Wizard container        -->
                <div class="wizard-container">
                    <div class="card wizard-card" data-color="blue" id="wizard">
                        <form action="" method="">
                            <!--        You can switch " data-color="green" "  with one of the next bright colors: "blue", "azure", "orange", "red"       -->

                            <div class="wizard-header">
                                <h3 class="wizard-title">Programmer une visite</h3>
                                <p class="category">Suivez les etapes pour programmer une visite.</p>
                            </div>
                            <div class="wizard-navigation">
                                <div class="progress-with-circle">
                                    <div class="progress-bar" role="progressbar" aria-valuenow="1" aria-valuemin="1"
                                         aria-valuemax="4" style="width: 15%;"></div>
                                </div>
                                <ul>
                                    <li>
                                        <a href="#type" data-toggle="tab">
                                            <div class="icon-circle">
                                                <i class="ti-direction-alt"></i>
                                            </div>
                                            Type
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#params" data-toggle="tab">
                                            <div class="icon-circle">
                                                <i class="ti-panel"></i>
                                            </div>
                                            Critéres
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#logements" data-toggle="tab">
                                            <div class="icon-circle">
                                                <i class="ti-home"></i>
                                            </div>
                                            logements
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#date" data-toggle="tab">
                                            <div class="icon-circle">
                                                <i class="ti-calendar"></i>
                                            </div>
                                            Date rdv
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#client" data-toggle="tab">
                                            <div class="icon-circle">
                                                <i class="ti-user"></i>
                                            </div>
                                            Client
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#description" data-toggle="tab">
                                            <div class="icon-circle">
                                                <i class="ti-list"></i>
                                            </div>
                                            Details
                                        </a>
                                    </li>
                                </ul>
                            </div>
                            <div class="tab-content">
                                <div class="tab-pane" id="type">
                                    <h5 class="info-text">Quel est le type de logement? </h5>
                                    <div class="row">
                                        <div class="col-sm-8 col-sm-offset-2">
                                            <div class="col-sm-4 col-sm-offset-2">
                                                <div class="choice" data-toggle="wizard-radio">
                                                    <input type="radio" class="active" checked id="villa" name="type"
                                                           value="villa">
                                                    <div class="card card-checkboxes card-hover-effect">
                                                        <i class="ti-home"></i>
                                                        <p>Villa</p>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-sm-4">
                                                <div class="choice" data-toggle="wizard-radio">
                                                    <input type="radio" class="active" checked id="appartement"
                                                           name="type"
                                                           value="appartement">
                                                    <div class="card card-checkboxes card-hover-effect">
                                                        <i class="far fa-building"></i>
                                                        <p>Appartement</p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="tab-pane" id="params">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <h5 class="info-text">Veuillez choisir une region.</h5>
                                        </div>
                                        <div class="col-sm-6 col-sm-offset-3">
                                            <div class="form-group">
                                                <label>Region</label><br>
                                                <select name="region" id="maregion" class="form-control">
                                                    <%LinkedList<Localite> localites = new LocaliteDAO().getAll(); %>
                                                    <%
                                                        for (Localite localite : localites) {
                                                            out.print("<option value=\""+localite.getId()+"\">"+localite.getNom()+" </option>");
                                                        }
                                                    %>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <h5 class="info-text">Tell us more about what yoou are looking
                                            for. </h5>
                                        <div class="col-sm-12 col-sm-offset-2 ">
                                            <div class="row">
                                                <div class="col-sm-12">
                                                    <label>Prix : </label><br>
                                                    <span>900 milles da</span>
                                                    <input id="prix" name="prix" type="text"
                                                           class="span2"
                                                           style="	width: 40%;"
                                                           value=""
                                                           data-slider-min="900" data-slider-max="50000"
                                                           data-slider-step="100" data-slider-value="[5000,11000]"/>
                                                    <span>50 000 milles da</span>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-sm-12">
                                                    <label>Superficie : </label><br>
                                                    <span>50m²</span>
                                                    <input id="superficie" name="superficie" type="text" class="span2"
                                                           style="	width: 350px;"
                                                           value=""
                                                           data-slider-min="50" data-slider-max="4000"
                                                           data-slider-step="10" data-slider-value="[600,1600]"/>
                                                    <span>4000m²</span>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="col-sm-12 ">
                                            <div class="row">
                                                <h5 class="info-text">Tell us more about facilities. </h5>
                                                <div class="row">
                                                    <div class="col-sm-2 col-sm-offset-2">
                                                        <div class="form-group">
                                                            <label>nbr de: pieces </label>
                                                            <input name="nbrPieces" id="nbrPieces" type="number"
                                                                   class="form-control"
                                                                   placeholder="4" value="1">
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-2 col-sm-offset-1">
                                                        <div class="form-group">
                                                            <label>nbr de: SDBs</label>
                                                            <input name="nbrSdb" id="nbrSdb" type="number"
                                                                   class="form-control"
                                                                   placeholder="1" value="1">
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-2 col-sm-offset-1">
                                                        <div class="form-group">
                                                            <label>nbr d'etages </label>
                                                            <input name="nbrEtages" id="nbrEtages" type="number"
                                                                   class="form-control"
                                                                   placeholder="1" value="1">
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-sm-2 col-sm-offset-2">
                                                    <div class="choice" data-toggle="wizard-checkbox">
                                                        <input type="checkbox" name="meuble" id="meuble">
                                                        <div class="card card-checkboxes card-hover-effect">
                                                            <i class="fas fa-couch"></i>
                                                            <p>Meublé</p>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-sm-2 ">
                                                    <div class="choice" data-toggle="wizard-checkbox">
                                                        <input type="checkbox" name="garage" id="garage">
                                                        <div class="card card-checkboxes card-hover-effect">
                                                            <i class="fas fa-warehouse"></i>
                                                            <p>Garage</p>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-sm-2">
                                                    <div class="choice" data-toggle="wizard-checkbox">
                                                        <input type="checkbox" name="jardin" id="jardin">
                                                        <div class="card card-checkboxes card-hover-effect">
                                                            <i class="fas fa-tree"></i>
                                                            <p>Jardin</p>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-sm-2 ">
                                                    <div class="choice" data-toggle="wizard-checkbox">
                                                        <input type="checkbox" name="soussol" id="soussol">
                                                        <div class="card card-checkboxes card-hover-effect">
                                                            <i class="fas fa-angle-double-down"></i>
                                                            <p>Sous-sol</p>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="tab-pane" id="logements">
                                    <div class="row">
                                        <h5 class="info-text"> Please tell us more about yourself.</h5>
                                        <div class="col-sm-12 col-sm-offset-0">
                                            <table id="logementsTable" class="table table-bordered table-hover">
                                                <thead>
                                                <tr>
                                                    <th>id</th>
                                                    <th>titre</th>
                                                    <th>price</th>
                                                    <th>superficie</th>
                                                    <th>adresse</th>
                                                    <th>description</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <tr>
                                                    <td>1</td>
                                                    <td>Berrehal</td>
                                                    <td>Ouala Eddine</td>
                                                    <td>200m²</td>
                                                    <td>7 000 000.00 da</td>
                                                </tr>
                                                <tr>
                                                    <td>2</td>
                                                    <td>Berrehal</td>
                                                    <td>Ouala Eddine</td>
                                                    <td>200m²</td>
                                                    <td>7 000 000.00 da</td>
                                                </tr>
                                                <tr>
                                                    <td>3</td>
                                                    <td>Berrehal</td>
                                                    <td>Ouala Eddine</td>
                                                    <td>200m²</td>
                                                    <td>7 000 000.00 da</td>
                                                </tr>
                                                <tr>
                                                    <td>4</td>
                                                    <td>Berrehal</td>
                                                    <td>Ouala Eddine</td>
                                                    <td>200m²</td>
                                                    <td>7 000 000.00 da</td>
                                                </tr>
                                                <tr>
                                                    <td>5</td>
                                                    <td>Berrehal</td>
                                                    <td>Ouala Eddine</td>
                                                    <td>200m²</td>
                                                    <td>7 000 000.00 da</td>
                                                </tr>
                                                </tbody>
                                                <!--  <tfoot>
                                                  <tr>
                                                      <th>Rendering engine</th>
                                                      <th>Browser</th>
                                                      <th>Platform(s)</th>
                                                      <th>Engine version</th>
                                                      <th>CSS grade</th>
                                                  </tr>
                                                  </tfoot>-->
                                            </table>
                                            <input type="hidden" name="selectedlogementId" id="selectedlogementId">
                                            <input type="hidden" name="selectedlogementprice"
                                                   id="selectedlogementprice">
                                            <input type="hidden" name="selectedlogementadresse"
                                                   id="selectedlogementadresse">
                                            <input type="hidden" name="selectedLogementSuperficie"
                                                   id="selectedLogementSuperficie">
                                        </div>
                                        <!-- /.box-body -->
                                    </div>
                                </div>

                                <div class="tab-pane" id="date">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <div id='calendar'></div>
                                            <input type="hidden" name="rdv" id="idVisite">
                                            <input type="hidden" name="rdv" id="idAgent">
                                            <input type="hidden" name="rdv" id="heureDebutVisite">
                                            <input type="hidden" name="rdv" id="heureFinVisite">
                                        </div>
                                    </div>
                                </div>

                                <div class="tab-pane" id="client">
                                    <div class="row">
                                        <h5 class="info-text"> Please tell us more about yourself.</h5>
                                        <div class="col-sm-12 col-sm-offset-0">
                                            <table id="clientsTab" class="table table-bordered table-hover">
                                                <thead>
                                                <tr>
                                                    <th>id</th>
                                                    <th>nom</th>
                                                    <th>prenom</th>
                                                    <th>date de naissance</th>
                                                    <th>telephone</th>
                                                    <th>isBanned</th>
                                                </tr>
                                                </thead>
                                                <tbody>

                                                </tbody>
                                                <!--  <tfoot>
                                                  <tr>
                                                      <th>Rendering engine</th>
                                                      <th>Browser</th>
                                                      <th>Platform(s)</th>
                                                      <th>Engine version</th>
                                                      <th>CSS grade</th>
                                                  </tr>
                                                  </tfoot>-->
                                            </table>
                                            <input type="text" name="clientId" id="clientId">
                                        </div>
                                        <!-- /.box-body -->
                                    </div>
                                </div>

                                <div class="tab-pane" id="description">
                                    <div class="row">
                                        <div class="row">
                                            <div class="col-xs-12 col-md-12 ">
                                                <div class="panel panel-success height">
                                                    <div class="panel-heading">Details du Rendez-vous</div>
                                                    <div class="panel-body">
                                                        <div class="col-xs-12 col-md-12 ">
                                                            <div class="panel panel-primary ">
                                                                <div class="panel-heading">Details du client</div>
                                                                <div class="panel-body">
                                                                    <div class=" col-md-6 ">
                                                                        <strong>Identifiant:</strong> <span
                                                                            id="clientIdDetails"></span><br>
                                                                        <strong>Numero de telephone:</strong>
                                                                        <span id="numeroTelephoneClient"></span>
                                                                        <br>
                                                                    </div>
                                                                    <div class=" col-md-6 ">
                                                                        <strong>Nom complet:</strong>
                                                                        <span id="nomCompletClient"></span>
                                                                        <br>
                                                                        <strong>Date de naissence:</strong>
                                                                        <span id="dateNaissClient"></span>
                                                                        <br>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-xs-12 col-md-12 ">
                                                            <div class="panel panel-info height">
                                                                <div class="panel-heading">Details du logement</div>
                                                                <div class="panel-body">
                                                                    <h5 align="center"><strong>ID logement :</strong>
                                                                        <span id="idLogementDetails"> </span>
                                                                        <br>
                                                                    </h5>
                                                                    <div class=" col-md-4 ">
                                                                        <strong>Region:</strong>
                                                                        <span id="regionDetails"></span>
                                                                        <br>
                                                                        <strong>type:</strong>
                                                                        <span id="typeDetails"></span>
                                                                        <br>
                                                                        <strong>Prix:</strong>
                                                                        <span id="prixDetails"> </span>
                                                                        <br>
                                                                        <strong>Superficie:</strong>
                                                                        <span id="superficieDetails"></span>
                                                                        <br>
                                                                    </div>
                                                                    <div class="col-md-4 ">
                                                                        <strong>nombre de pieces:</strong>
                                                                        <span id="nbrPiecesDetails"></span>
                                                                        <br>
                                                                        <strong>nombre d'etages:</strong>
                                                                        <span id="nbrEtagesDetails"></span>
                                                                        <br>
                                                                        <strong>nombre de SDBs:</strong>
                                                                        <span id="nbrSDBDetails"></span>
                                                                        <br>

                                                                    </div>
                                                                    <div class="col-md-4 ">
                                                                        <strong>avec sous-sol:</strong>
                                                                        <span id="sousSolDetails"></span>
                                                                        <br>
                                                                        <strong>avec garage:</strong>
                                                                        <span id="garageDetails"></span>
                                                                        <br>
                                                                        <strong>avec jardin:</strong>
                                                                        <span id="jardinDetails"></span>
                                                                        <br>
                                                                        <strong>Meublé:</strong>
                                                                        <span id="meubleDetails"></span>
                                                                        <br>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-xs-12 col-md-12 ">
                                                            <div class="panel panel-danger height">
                                                                <div class="panel-heading">Details de la visite</div>
                                                                <div class="panel-body">
                                                                    <div class=" col-md-4 ">
                                                                        <strong>Agent ID:</strong>
                                                                        <span id="idAgentDetails"></span>
                                                                        <br>
                                                                        <strong>Date:</strong>
                                                                        <span id="dateVisiteDetails"></span>
                                                                        <br>
                                                                    </div>
                                                                    <div class="col-md-4 ">
                                                                        <strong>Nom Agent:</strong>
                                                                        <span id="nomAgentDetails"></span>
                                                                        <br>
                                                                        <strong>Heure:</strong>
                                                                        <span id="heureDetails"></span>
                                                                        <br>
                                                                    </div>
                                                                    <div class="col-md-4 ">
                                                                        <strong>telephone:</strong>
                                                                        <span id="tellAgentDetails"></span>
                                                                        <br>
                                                                        <strong>Durée:</strong>
                                                                        <span id="dureeVisiteDetails"></span>
                                                                        <br>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="wizard-footer">
                                <div class="pull-right">
                                    <input type='button' class='btn btn-next btn-fill btn-success btn-wd'
                                           name='next'
                                           value='Next'/>
                                    <input type='button' class='btn btn-finish btn-fill btn-success btn-wd'
                                           name='finish' value='Finish' onclick="confirmerVisite()"/>
                                </div>

                                <div class="pull-left">
                                    <input type='button' class='btn btn-previous btn-default btn-wd'
                                           name='previous'
                                           value='Previous'/>
                                </div>
                                <div class="clearfix"></div>
                            </div>
                        </form>
                    </div>
                </div> <!-- wizard container -->
            </div>
        </div> <!-- row -->
    </div> <!--  big container -->
</div>
<!--Ajouter client modal-->
<div id="ajouterClientModal" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Ajouter client</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
                <form method="post" action="/AjoutServlet?ajouter=client" id="ajouterClientForm">
                    <div class="box-for overflow">
                        <div class="col-md-12 col-xs-12 register-blocks">
                            <h2>Inscription : </h2>
                            <div class="form-group">
                                <label for="nomInput">Nom</label>
                                <input type="text" class="form-control" id="nomInput" name="nomInput">

                                <label for="prenomInput">Prenom</label>
                                <input type="text" class="form-control" id="prenomInput" name="prenomInput">

                                <label for="dateNaissance">Date de naissance</label>
                                <input type="date" class="form-control" id="dateNaissance" name="dateNaissance">
                            </div>
                            <div class="form-group">
                                <label for="emailInput">Email</label>
                                <input type="text" class="form-control" id="emailInput" name="emailInput">

                                <label for="inputTel">Numéro de téléphone</label>
                                <input type="text" class="form-control" id="inputTel" name="inputTel">

                                <label for="adresseInput">Adresse</label>
                                <input type="text" class="form-control" id="adresseInput" name="adresseInput">

                            </div>
                            <div class="form-group">
                                <label for="usernameInput">nom d'utilisateur</label>
                                <input type="text" class="form-control" id="usernameInput" name="usernameInput">

                                <label for="passwordInput">Mot de passe</label>
                                <input type="password" class="form-control" id="passwordInput" name="passwordInput">
                            </div>
                            <div class="text-center">
                                <button type="button" class="btn btn-default" onclick="ajouterClient()">Inscription</button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
            </div>
        </div>
    </div>
</div>
</body>

<!--   Core JS Files   -->
<script src="./programmerVisite/assets/js/jquery-2.2.4.min.js" type="text/javascript"></script>
<script src="./programmerVisite/assets/js/bootstrap.min.js" type="text/javascript"></script>
<script src="./programmerVisite/assets/js/jquery.bootstrap.wizard.js" type="text/javascript"></script>
<script src="./programmerVisite/assets/js/bootstrap-slider.js" type="text/javascript"></script>

<!--  Plugin for the Wizard -->
<!--<script src="programmerVisite/assets/js/paper-bootstrap-wizard.js" type="text/javascript"></script>-->

<!--  More information about jquery.validate here: http://jqueryvalidation.org/	 -->
<script src="./programmerVisite/assets/js/jquery.validate.min.js" type="text/javascript"></script>


<!-- fullCalendar -->
<script src="./programmerVisite/assets/moment/moment.js"></script>
<script src="./programmerVisite/assets/fullcalendar/dist/fullcalendar.min.js"></script>
<script src="./programmerVisite/assets/fullcalendar/dist/locale/fr.js"></script>

<!-- DataTables -->
<script src="./programmerVisite/assets/datatables.net/js/jquery.dataTables.min.js"></script>
<script src="./programmerVisite/assets/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
<script src="./programmerVisite/assets/datatables-select/dataTables.select.min.js"></script>
<script src="./programmerVisite/assets/datatables-responsive/dataTables.responsive.min.js"></script>
<script src="./programmerVisite/assets/js/datatable-buttons.js"></script>
<script src="./programmerVisite/assets/js/main.js"></script>

<script>
    function ajouterClient(){


        $.ajax({
            type: "POST",
            url: "/AjoutServlet?ajouter=client",
            data: {
                prenomInput:$('#prenomInput').val(),
                nomInput: $('#nomInput').val(),
                emailInput: $('#emailInput').val(),
                inputTel: $('#inputTel').val() ,
                usernameInput: $('#usernameInput').val(),
                passwordInput: $('#passwordInput').val(),
                adresseInput: $('#adresseInput').val(),
                dateNaissance: $('#dateNaissance').val()
            },

            success: function (result) {
                loadClients();
                $('#ajouterClientModal').modal('hide');
            }
        });
    }




</script>

</html>































