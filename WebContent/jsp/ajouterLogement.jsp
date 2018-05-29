<%@ page import="java.util.LinkedList" %>
<%@ page import="model.beans.Localite" %>
<%@ page import="model.db.daos.LocaliteDAO" %>
<%@ page import="model.enums.UserType" %>
<%@ page import="model.beans.views.TablesView" %>
<%@ page import="control.servlets.MyServlet" %>
<%@ page import="model.beans.humans.Employe" %>
<%@ page import="model.beans.views.MyView" %>
<!DOCTYPE html>
<html lang="en">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%! private TablesView tablesView = new TablesView(); %>
<%
    UserType userType = (UserType) request.getSession().getAttribute(MyServlet.LOGGED_IN_USER_TYPE);
    Employe employe = (Employe) request.getSession().getAttribute(MyServlet.LOGGED_IN_USER);
    int userId = (int) request.getSession().getAttribute(MyServlet.LOGGED_IN_USER_ID);
    tablesView.setLoggedInUserId(userId);
    tablesView.setLoggedInUserType(userType);
%>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Ajouter un logement</title>
    <!-- Bootstrap core CSS-->
    <link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom fonts for this template-->
    <link href="../vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <!-- Custom styles for this template-->
    <link href="../css/sb-admin.css" rel="stylesheet">
    <link href="../css/bootstrapValidator.min.css" rel="stylesheet"/>
</head>

<body class="bg-white">
<nav class="navbar navbar-expand-lg navbar-dark navbar-<%out.print(tablesView.getNav().getCssBackgroundClass());%> sidebar fixed-top fixed-top "
     id="mainNav">
    <a class="navbar-brand" href="#"><%out.print(tablesView.getNav().getTitle()+": "+employe.getNom()+" "+employe.getPrenom());%></a>
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
<div class="container" style="margin-top: 10%">
    <div class="card card-register mx-auto mt-5">
        <div class="card-header">Ajouter un logement</div>
        <div class="card-body">
            <form method="post" action="/AjoutServlet?ajouter=logement" id="ajouterLogementForm">
                <div class="form-group">
                    <div class="form-row">
                        <label for="titreInput">Titre</label>
                        <input class="form-control" id="titreInput" name="titreInput" type="text" aria-describedby="nameHelp" placeholder="Titre">

                    </div>
                    <div class="form-row">
                        <label for="description">Description</label>
                        <input class="form-control" id="description" name="description" type="text" aria-describedby="nameHelp" placeholder="Description">
                    </div>
                    <div class="form-row">
                        <label for="adresse">Adresse</label>
                        <input class="form-control" id="adresse" name="adresse" type="text" aria-describedby="nameHelp" placeholder="Adresse">
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-row">
                        <div class="col-md-4">
                            <label for="typeLogement">Type</label>
                            <select class="custom-select" id="typeLogement" name="typeLogement">
                                <option value="villa">Villa</option>
                                <option value="appartement">Appartement</option>
                            </select>
                        </div>
                        <div class="col-md-4">
                            <label for="superficie">Superficie</label>
                            <input class="form-control" id="superficie" name="superficie" type="number" placeholder="Superficie">
                        </div>
                        <div class="col-md-4">
                            <label for="region">Region</label>
                            <select class="custom-select" name="region" id="region">
                                <%
                                    LinkedList<Localite> list = new LocaliteDAO().getAll();
                                    for (Localite localite : list) {
                                        out.print("<option value=\""+localite.getId()+"\">"+localite.getNom()+"</option>");
                                    }
                                %>
                            </select>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col-md">
                            <label for="nbrPcs">Pièces</label>
                            <input class="form-control" id="nbrPcs" name="nbrPcs" type="number" placeholder="Nombre de pièces">
                        </div>
                        <div class="col-md">
                            <label for="nbrSdb">Salles de bain</label>
                            <input class="form-control" id="nbrSdb" name="nbrSdb" type="number" placeholder="Nombre de sdb">
                        </div>
                        <div class="col-md">
                            <label for="etage">Etage</label>
                            <input class="form-control" id="etage" name="etage" type="number" aria-describedby="emailHelp" placeholder="Etage">
                        </div>

                    </div>
                    <div class="form-row">
                        <div class="col-md">
                            <label for="jardin">Avec jardin</label>
                            <input name="jardin" type="checkbox" class="form-control" id="jardin" value="jardin" placeholder="Avec jardin">
                        </div>
                        <div class="col-md">
                            <label for="garage">Avec garage</label>
                            <input name="garage" type="checkbox" class="form-control" id="garage" value="garage" placeholder="Avec garage">
                        </div>
                        <div class="col-md">
                            <label for="soussol">Avec sous-sol</label>
                            <input name="soussol" type="checkbox" class="form-control" id="soussol" value="soussol" placeholder="Avec sous-sol">
                        </div>
                        <div class="col-md">
                            <label for="meubles">Meublé</label>
                            <input name="meubles" id="meubles" type="checkbox" class="form-control" value="meublespapapa">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col-md">
                            <label for="prix">Prix</label>
                            <input class="form-control" id="prix" name="prix" type="text" placeholder="Prix">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col-md">
                            <label for="latitude">Latitude</label>
                            <input class="form-control" id="latitude" name="latitude" type="text"  placeholder="Latitude">
                        </div>
                        <div class="col-md">
                            <label for="longitude">Longitude</label>
                            <input class="form-control" id="longitude" name="longitude" type="text" placeholder="Longitude">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="image">Photo</label>
                        <input id="image" class="form-control" type="file" name="image" placeholder="Photo">
                    </div>
                </div>
                <div class="text-center">
                    <button class="btn btn-primary" type="submit" form="ajouterLogementForm">Ajouter</button>
                </div>


            </form>
        </div>
    </div>
</div>
<!-- Bootstrap core JavaScript-->
<script src="../vendor/jquery/jquery.min.js"></script>
<script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- Core plugin JavaScript-->
<script src="../vendor/jquery-easing/jquery.easing.min.js"></script>
<script src="../js/bootstrapValidator.min.js"></script>
<script>
//    $('#submitBtn').click(function() {
//        /* when the button in the form, display the entered values in the modal */
//        $('#lname').text($('#lastname').val());
//        $('#fname').text($('#firstname').val());
//    });


    $(document).ready(function () {
        var validator = $("#ajouterLogementForm").bootstrapValidator({
            fields:{
                titreInput:{
                    validators:{
                        notEmpty:{
                            message:"Veuillez entrer un titre"
                        }
                    }
                },
                description:{
                    validators:{
                        notEmpty:{
                            message:"Veuillez entrer une description"
                        }
                    }
                },
                adresse:{
                    validators:{
                        notEmpty:{
                            message:"Veuillez entrer une adresse"
                        }
                    }
                },
                superficie:{
                    validators:{
                        notEmpty:{
                            message:"Veuillez entrer une adresse"
                        }
                    }
                },
                nbrPcs:{
                    validators:{
                        notEmpty:{
                            message:"Veuillez entrer le nombre de pièces"
                        },
                        integer:{
                            message:"Veuillez entrer un nombre entier"
                        }
                    }
                },
                nbrSdb:{
                    validators:{
                        notEmpty:{
                            message:"Veuillez entrer le nombre de salles de bain"
                        },
                        integer:{
                            message:"Veuillez entrer un nombre entier"
                        }
                    }
                },
                etage:{
                    validators:{
                        notEmpty:{
                            message:"Veuillez entrer l'étage"
                        },
                        integer:{
                            message:"Veuillez entrer un nombre entier"
                        }
                    }
                },
                prix:{
                    validators:{
                        notEmpty:{
                            message:"Veuillez entrer le prix"
                        }
                    }
                },
                latitude:{
                    validators:{
                        notEmpty:{
                            message:"Veuillez entrer la latitude"
                        }
                    }
                },
                longitude:{
                    validators:{
                        notEmpty:{
                            message:"Veuillez entrer la longitutde"
                        }
                    }
                }
            }
        })
    });

</script>
<style>
    .has-error .help-block {
        color: red;
    }
    small.help-block {
        color: #F44336 !important;
    }
</style>
</body>

</html>