package tests;


//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.Parameterized;

//import static org.junit.Assert.assertTrue;
//
//@RunWith(Parameterized.class)
//public class AgentsManagerTest {
//
//    private String etatClient, avis, commentaire;
//    private int visiteID, idagent;
//
//    public AgentsManagerTest(String etatClient, int visiteID, String avis, String commentaire, int idagent) {
//        this.etatClient = etatClient;
//        this.visiteID = visiteID;
//        this.avis = avis;
//        this.commentaire = commentaire;
//        this.idagent = idagent;
//    }
//
//    @Parameterized.Parameters
//    public static Collection listeVariable() {
//        return Arrays.asList(new Object[][]{
//                /*0*/  {"present", 1, "positif", "comment1", 1}/* false psk kayn deja rapport ta3 visite 1*/,
//                /*1*/  {"present", 2, "negatif", "comment15", 1} /* true nrml */,
//                /*2*/  {"present", 7, "positif", "comment1", 1}/* kifkif true*/,
//                /*3*/  {"absent", 3, "positif", "comment11", 1/* mauqdersh mayjish w tkoun avis positif*/},
//                /*4*/  {"absent", 4, "negatif", "comment1", 1}/* tessloh true */,
//                /*5*/  {"present", 5, "negatif", "comment2", 12}/* metsslohsh belek psq makansh agentid = 12*/,
//                /*6*/  {"absent", 77, "positif", "comment44", 80} /* metsslohsh khlass psq absent positif makansh menha w makansh l agent 80 w lclient 77*/
//        });
//    }
//
//    @Test
//    public void envoyerRapportTrue() {
//        Employe employe = new Employe();
//        employe.setId(idagent);
//
//        AgentsManager agentManager = new AgentsManager(employe);
//        assertTrue(agentManager.envoyerRapport(etatClient, visiteID, avis, commentaire));
//    }
//}