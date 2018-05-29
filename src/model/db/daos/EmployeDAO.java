package model.db.daos;

import model.beans.humans.Employe;
import model.db.DAO;
import model.enums.UserType;
import utils.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

@SuppressWarnings("ALL")
public class EmployeDAO extends DAO {

    public boolean inscriptionEmploye(Employe employe) {
        try {
            employeStatement.execute("INSERT INTO employe (`nom`,`prenom`,`dateNaiss`,`adresse`,`tel`,`email`,`username`, `password`,`isApproved`,`isSuspended` ) VALUES(" +
                    "'" + employe.getNom() + "'," +
                    "'" + employe.getPrenom() + "'," +
                    "'" + employe.getDateNaissance() + "'," +
                    "'" + employe.getAdresse() + "'," +
                    "'" + employe.getTel() + "'," +
                    "'" + employe.getEmail() + "'," +
                    "'" + employe.getUsername() + "'," +
                    "'" + employe.getPassword() + "'," +
                    "0, " +
                    "0" +
                    ");");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean approuverEmploye(int id, int myId, String userType) {
        try {
            employeStatement.execute("UPDATE employe SET isApproved = 1, addedBy = " + myId + ",userType='" + userType + "',dateAdded=CURRENT_DATE WHERE id=" + id + ";");
//            employeStatement.execute("UPDATE employe SET isApproved=1 WHERE id=" + id + ";");
//            employeStatement.execute("UPDATE employe SET addedBy = "+ myId +" WHERE id="+ id +";");
//            employeStatement.execute("UPDATE employe SET userType = '" + userType + "' WHERE id=" + id + ";");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean changePassword(int id, String pass) {
        try {
            employeStatement.execute("UPDATE employe SET password='" + pass + "' WHERE id=" + id);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Employe getByUsername(String username) {
        ResultSet result;
        try {
            result = employeStatement.executeQuery("SELECT * FROM employe WHERE username='" + username + "'");
            if (result.next()) {
                Employe employe = new Employe();
                employe.setId(result.getInt("id"));
                employe.setNom(result.getString("nom"));
                employe.setPrenom(result.getString("prenom"));
                employe.setDateNaissance(result.getDate("dateNaiss"));
                employe.setAdresse(result.getString("adresse"));
                employe.setTel(result.getString("tel"));
                employe.setEmail(result.getString("email"));
                employe.setUsername(result.getString("username"));
                employe.setPassword(result.getString("password"));
                employe.setDateAdded(result.getDate("dateAdded"));
                employe.setApproved(result.getBoolean("isApproved"));
                employe.setSuspended(result.getBoolean("isSuspended"));
                employe.setUserType(Util.getUserTypeFromString(result.getString("userType")));

                return employe;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Employe getByEmail(String email) {
        ResultSet result;
        try {
            result = employeStatement.executeQuery("SELECT * FROM employe WHERE email = '" + email + "';");
            if (result.next()) {
                Employe employe = new Employe();
                employe.setId(result.getInt("id"));
                employe.setNom(result.getString("nom"));
                employe.setPrenom(result.getString("prenom"));
                employe.setDateNaissance(result.getDate("dateNaiss"));
                employe.setAdresse(result.getString("adresse"));
                employe.setTel(result.getString("tel"));
                employe.setEmail(result.getString("email"));
                employe.setUsername(result.getString("username"));
                employe.setPassword(result.getString("password"));
                employe.setSuspended(result.getBoolean("isSuspended"));
                employe.setApproved(result.getBoolean("isApproved"));
                if (employe.isApproved()) {
                    employe.setDateAdded(result.getDate("dateAdded"));
                    employe.setUserType(Util.getUserTypeFromString(result.getString("userType")));
                    employe.setCreator(result.getInt("addedBy"));
                }
                return employe;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object getById(int id) {
        ResultSet result;
        try {
            result = employeStatement.executeQuery("SELECT * FROM employe WHERE id=" + id);
            if (result.next()) {
                Employe employe = new Employe();
                employe.setId(result.getInt("id"));
                employe.setNom(result.getString("nom"));
                employe.setPrenom(result.getString("prenom"));
                employe.setDateNaissance(result.getDate("dateNaiss"));
                employe.setAdresse(result.getString("adresse"));
                employe.setTel(result.getString("tel"));
                employe.setEmail(result.getString("email"));
                employe.setUsername(result.getString("username"));
                employe.setPassword(result.getString("password"));
                employe.setSuspended(result.getBoolean("isSuspended"));
                employe.setApproved(result.getBoolean("isApproved"));
                if (employe.isApproved()) {
                    employe.setDateAdded(result.getDate("dateAdded"));
                    employe.setUserType(Util.getUserTypeFromString(result.getString("userType")));
                    employe.setCreator(result.getInt("addedBy"));
                }
                return employe;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public boolean deleteById(int id) {
        try {
            employeStatement.execute("DELETE FROM employe WHERE id=" + id);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Object object) {
        Employe employe = (Employe) object;
        try {
            employeStatement.execute("UPDATE employe SET " +
                    "nom = '" + employe.getNom() + "'," +
                    "prenom = '" + employe.getPrenom() + "'," +
                    "tel = '" + employe.getTel() + "'," +
                    "dateNaiss = '" + employe.getDateNaissance() + "'," +
                    "adresse='" + employe.getAdresse() + "'," +
                    "email='" + employe.getEmail() + "'," +
                    "username='" + employe.getUsername() + "'" +
                    " WHERE employe.id=" + employe.getId() + ";");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean add(Object object) {
        Employe employe = (Employe) object;
        try {
            employeStatement.execute("INSERT INTO employe (`nom`,`prenom`,`dateNaiss`,`adresse`,`tel`,`email`,`username`, `password`,`dateAdded`,`addedBy`,`isSuspended`,`userType` ) VALUES(" +
                    "'" + employe.getNom() + "'," +
                    "'" + employe.getPrenom() + "'," +
                    "'" + employe.getDateNaissance() + "'" + "," +
                    "'" + employe.getAdresse() + "'," +
                    "'" + employe.getTel() + "'," +
                    "'" + employe.getEmail() + "'," +
                    "'" + employe.getUsername() + "'," +
                    "'" + employe.getPassword() + "'," +
                    "CURRENT_DATE" + "," +
                    employe.getCreator() + "," +
                    employe.isSuspended() + "," +
                    "'" + Util.getStringFromType(employe.getUserType()) + "'" +
                    ");");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Object object) {
        return false;
    }

    public LinkedList<Employe> getAll() {
        LinkedList<Employe> list = new LinkedList<>();
        ResultSet result;
        try {
            result = employeStatement.executeQuery("SELECT * FROM employe;");
            while (result.next()) {
                Employe employe = new Employe();
                employe.setId(result.getInt("id"));
                employe.setNom(result.getString("nom"));
                employe.setPrenom(result.getString("prenom"));
                employe.setDateNaissance(result.getDate("dateNaiss"));
                employe.setAdresse(result.getString("adresse"));
                employe.setTel(result.getString("tel"));
                employe.setEmail(result.getString("email"));
                employe.setUsername(result.getString("username"));
                employe.setPassword(result.getString("password"));
                employe.setDateAdded(result.getDate("dateAdded"));
                employe.setSuspended(result.getBoolean("isSuspended"));
                employe.setUserType(Util.getUserTypeFromString(result.getString("userType")));

                list.add(employe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int countAll() {
        ResultSet result;
        try {
            result = employeStatement.executeQuery("SELECT count(id) FROM employe;");
            if (result.next()) {
                return result.getInt("count(id)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean authenticate(String username, String password) {
        ResultSet result;
        try {
            result = employeStatement.executeQuery("SELECT id,isSuspended,isApproved FROM employe WHERE ((username='" + username + "' AND password='" + password + "') OR (email='" + username + "' AND password='" + password + "')) AND isSuspended=0 AND isApproved=1");
            return (result.next());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean reintegrerById(int id) {
        return super.reintegrerById(id, "employe");
    }

    public boolean suspendById(int userId) {
        return super.suspendById(userId, "employe");
    }

    public LinkedList<Employe> getSuspendedResVente() {
        LinkedList<Employe> list = new LinkedList<>();
        ResultSet result;
        try {
            result = employeStatement.executeQuery("SELECT * FROM employe WHERE userType='responsable_vente' AND isSuspended=1 AND isApproved=1;");
            while (result.next()) {
                Employe employe = new Employe();
                employe.setId(result.getInt("id"));
                employe.setNom(result.getString("nom"));
                employe.setPrenom(result.getString("prenom"));
                employe.setDateNaissance(result.getDate("dateNaiss"));
                employe.setAdresse(result.getString("adresse"));
                employe.setTel(result.getString("tel"));
                employe.setEmail(result.getString("email"));
                employe.setUsername(result.getString("username"));
                employe.setPassword(result.getString("password"));
                employe.setSuspended(result.getBoolean("isSuspended"));
                employe.setApproved(result.getBoolean("isApproved"));
                if (employe.isApproved()) {
                    employe.setDateAdded(result.getDate("dateAdded"));
                    employe.setUserType(Util.getUserTypeFromString(result.getString("userType")));
                    employe.setCreator(result.getInt("addedBy"));
                }
                list.add(employe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public LinkedList<Employe> getAllResVentes() {
        ResultSet result;
        LinkedList<Employe> list = new LinkedList<>();
        try {
            result = employeStatement.executeQuery("SELECT * FROM employe WHERE userType='responsable_ventes' AND isApproved=1;");
            while (result.next()) {
                Employe employe = new Employe();
                employe.setId(result.getInt("id"));
                employe.setNom(result.getString("nom"));
                employe.setPrenom(result.getString("prenom"));
                employe.setDateNaissance(result.getDate("dateNaiss"));
                employe.setAdresse(result.getString("adresse"));
                employe.setTel(result.getString("tel"));
                employe.setEmail(result.getString("email"));
                employe.setUsername(result.getString("username"));
                employe.setPassword(result.getString("password"));
                employe.setSuspended(result.getBoolean("isSuspended"));
                employe.setApproved(result.getBoolean("isApproved"));
                if (employe.isApproved()) {
                    employe.setDateAdded(result.getDate("dateAdded"));
                    employe.setUserType(Util.getUserTypeFromString(result.getString("userType")));
                    employe.setCreator(result.getInt("addedBy"));
                }
                list.add(employe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public LinkedList<Employe> getSuspendedOperateurs() {
        LinkedList<Employe> list = new LinkedList<>();
        ResultSet result;
        try {
            result = employeStatement.executeQuery("SELECT id FROM employe WHERE userType='operateur' AND isSuspended=1 AND isApproved=1;");
            while (result.next()) {
                Employe employe = new Employe();
                employe.setId(result.getInt("id"));
                employe.setNom(result.getString("nom"));
                employe.setPrenom(result.getString("prenom"));
                employe.setDateNaissance(result.getDate("dateNaiss"));
                employe.setAdresse(result.getString("adresse"));
                employe.setTel(result.getString("tel"));
                employe.setEmail(result.getString("email"));
                employe.setUsername(result.getString("username"));
                employe.setPassword(result.getString("password"));
                employe.setSuspended(result.getBoolean("isSuspended"));
                employe.setApproved(result.getBoolean("isApproved"));
                if (employe.isApproved()) {
                    employe.setDateAdded(result.getDate("dateAdded"));
                    employe.setUserType(Util.getUserTypeFromString(result.getString("userType")));
                    employe.setCreator(result.getInt("addedBy"));
                }
                list.add(employe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public LinkedList<Employe> getAllOperateurs() {
        ResultSet result;
        LinkedList<Employe> list = new LinkedList<>();
        try {
            result = employeStatement.executeQuery("SELECT * FROM employe WHERE userType='operateur' AND isApproved=1;");
            while (result.next()) {

                Employe employe = new Employe();
                employe.setId(result.getInt("id"));
                employe.setNom(result.getString("nom"));
                employe.setPrenom(result.getString("prenom"));
                employe.setDateNaissance(result.getDate("dateNaiss"));
                employe.setAdresse(result.getString("adresse"));
                employe.setTel(result.getString("tel"));
                employe.setEmail(result.getString("email"));
                employe.setUsername(result.getString("username"));
                employe.setPassword(result.getString("password"));
                employe.setSuspended(result.getBoolean("isSuspended"));
                employe.setApproved(result.getBoolean("isApproved"));
                if (employe.isApproved()) {
                    employe.setDateAdded(result.getDate("dateAdded"));
                    employe.setUserType(Util.getUserTypeFromString(result.getString("userType")));
                    employe.setCreator(result.getInt("addedBy"));
                }

                list.add(employe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public LinkedList<Employe> getSuspendedAgents() {
        LinkedList<Employe> list = new LinkedList<>();
        ResultSet result;
        try {
            result = employeStatement.executeQuery("SELECT id FROM employe WHERE userType='agent' AND isSuspended=1 AND isApproved=1;");
            while (result.next()) {
                Employe employe = new Employe();
                employe.setId(result.getInt("id"));
                employe.setNom(result.getString("nom"));
                employe.setPrenom(result.getString("prenom"));
                employe.setDateNaissance(result.getDate("dateNaiss"));
                employe.setAdresse(result.getString("adresse"));
                employe.setTel(result.getString("tel"));
                employe.setEmail(result.getString("email"));
                employe.setUsername(result.getString("username"));
                employe.setPassword(result.getString("password"));
                employe.setSuspended(result.getBoolean("isSuspended"));
                employe.setApproved(result.getBoolean("isApproved"));
                if (employe.isApproved()) {
                    employe.setDateAdded(result.getDate("dateAdded"));
                    employe.setUserType(Util.getUserTypeFromString(result.getString("userType")));
                    employe.setCreator(result.getInt("addedBy"));
                }

                list.add(employe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public LinkedList<Employe> getAllAgents() {
        ResultSet result;
        LinkedList<Employe> list = new LinkedList<>();
        try {
            result = employeStatement.executeQuery("SELECT * FROM employe WHERE userType='agent' AND isApproved=1;");
            while (result.next()) {
                Employe employe = new Employe();
                employe.setId(result.getInt("id"));
                employe.setNom(result.getString("nom"));
                employe.setPrenom(result.getString("prenom"));
                employe.setDateNaissance(result.getDate("dateNaiss"));
                employe.setAdresse(result.getString("adresse"));
                employe.setTel(result.getString("tel"));
                employe.setEmail(result.getString("email"));
                employe.setUsername(result.getString("username"));
                employe.setPassword(result.getString("password"));
                employe.setSuspended(result.getBoolean("isSuspended"));
                employe.setApproved(result.getBoolean("isApproved"));
                if (employe.isApproved()) {
                    employe.setDateAdded(result.getDate("dateAdded"));
                    employe.setUserType(Util.getUserTypeFromString(result.getString("userType")));
                    employe.setCreator(result.getInt("addedBy"));
                }

                list.add(employe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public LinkedList<Employe> getAllAdmins() {
        ResultSet result;
        LinkedList<Employe> list = new LinkedList<>();
        try {
            result = employeStatement.executeQuery("SELECT * FROM employe WHERE (userType='admin' OR userType='SU') AND isApproved=1;");
            while (result.next()) {
                Employe employe = new Employe();
                employe.setId(result.getInt("id"));
                employe.setNom(result.getString("nom"));
                employe.setPrenom(result.getString("prenom"));
                employe.setDateNaissance(result.getDate("dateNaiss"));
                employe.setAdresse(result.getString("adresse"));
                employe.setTel(result.getString("tel"));
                employe.setEmail(result.getString("email"));
                employe.setUsername(result.getString("username"));
                employe.setPassword(result.getString("password"));
                employe.setSuspended(result.getBoolean("isSuspended"));
                employe.setApproved(result.getBoolean("isApproved"));
                if (employe.isApproved()) {
                    employe.setDateAdded(result.getDate("dateAdded"));
                    employe.setUserType(Util.getUserTypeFromString(result.getString("userType")));
                    employe.setCreator(result.getInt("addedBy"));
                }

                list.add(employe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public LinkedList<Employe> getSuspendedAdmins() {
        ResultSet result;
        LinkedList<Employe> list = new LinkedList<>();
        try {
            result = employeStatement.executeQuery("SELECT * FROM employe WHERE userType='admin' AND isSuspended=1 AND isApproved=1;");
            while (result.next()) {
                Employe employe = new Employe();
                employe.setId(result.getInt("id"));
                employe.setNom(result.getString("nom"));
                employe.setPrenom(result.getString("prenom"));
                employe.setDateNaissance(result.getDate("dateNaiss"));
                employe.setAdresse(result.getString("adresse"));
                employe.setTel(result.getString("tel"));
                employe.setEmail(result.getString("email"));
                employe.setUsername(result.getString("username"));
                employe.setPassword(result.getString("password"));
                employe.setSuspended(result.getBoolean("isSuspended"));
                employe.setApproved(result.getBoolean("isApproved"));
                if (employe.isApproved()) {
                    employe.setDateAdded(result.getDate("dateAdded"));
                    employe.setUserType(Util.getUserTypeFromString(result.getString("userType")));
                    employe.setCreator(result.getInt("addedBy"));
                }

                list.add(employe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public LinkedList<Employe> getNotApprovedEmployees() {
        LinkedList<Employe> list = new LinkedList<>();
        ResultSet result;
        try {
            result = employeStatement.executeQuery("SELECT * FROM employe WHERE isApproved=0;");
            while (result.next()) {
                Employe employe = new Employe();
                employe.setId(result.getInt("id"));
                employe.setNom(result.getString("nom"));
                employe.setPrenom(result.getString("prenom"));
                employe.setDateNaissance(result.getDate("dateNaiss"));
                employe.setAdresse(result.getString("adresse"));
                employe.setTel(result.getString("tel"));
                employe.setEmail(result.getString("email"));
                employe.setUsername(result.getString("username"));
                employe.setPassword(result.getString("password"));
                employe.setSuspended(result.getBoolean("isSuspended"));
                employe.setApproved(result.getBoolean("isApproved"));
                if (employe.isApproved()) {
                    employe.setDateAdded(result.getDate("dateAdded"));
                    employe.setUserType(Util.getUserTypeFromString(result.getString("userType")));
                    employe.setCreator(result.getInt("addedBy"));
                }

                list.add(employe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    /**
     * Stats
     */
    public int countEmployeeByType(UserType agent) {
        String type = Util.getStringFromType(agent);
        ResultSet result;
        try {
            result = employeStatement.executeQuery("SELECT count (id) from employe where userType='" + type + "';");
            if (result.next()) {
                return result.getInt("count(id)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int notApprovedNbrByType(UserType userType) {
        ResultSet result;
        String type = Util.getStringFromType(userType);
        try {
            result = employeStatement.executeQuery("SELECT count(id) from employe where userType='" + type + "' and isApproved=0;");
            if (result.next()) {
                return result.getInt("count(id)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    public int myReportedVisitsNbr(int userId) {
        ResultSet result;
        try {
            result = employeStatement.executeQuery("select count(id) from visite where visite.timestamp>=current_date  and etat='reportee' and agentId=" + userId + ";");
            if (result.next()) {
                return result.getInt("count(id)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int myCanceledVisitsNbr(int userId) {
        ResultSet result;
        try {
            result = employeStatement.executeQuery("select count(id) from visite where visite.timestamp>=current_date  and  etat='annulee' and agentId=" + userId + ";");
            if (result.next()) {
                return result.getInt("count(id)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int myProgrammedVisitsNbr(int userId) {
        ResultSet result;
        try {
            result = employeStatement.executeQuery("select count(id) from visite where visite.timestamp>=current_date and etat='prevue' and agentId=" + userId + ";");
            if (result.next()) {
                return result.getInt("count(id)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int myVisitedLogementsNbr(int userId) {
        ResultSet result;
        try {
            result = employeStatement.executeQuery("select count(distinct visite.logementId) as nbr from visite where visite.agentId=" + userId + ";");
            if (result.next()) {
                return result.getInt("nbr");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean tokenExists(String token) {
        ResultSet result;
        try {
            result = employeStatement.executeQuery("select * from employe_push_tokens where token='" + token + "';");
            return result.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateToken(int userId, String token) {
        try {
            employeStatement.execute("update employe_push_tokens set token = '" + token + "' where employeId=" + userId + ";");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addToken(int userId, String token) {
        try {
            employeStatement.execute("insert into employe_push_tokens (employeId, token) values (" +
                    userId + "," +
                    "'" + token + "'" +
                    ");");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int newNotifsNbrForAgent(int userId) {
        ResultSet result;
        try {
            result = employeStatement.executeQuery("select count(id) as nbr from notification_employe where destinataire=" + userId + " and DATE(timestamp)=current_date ;");
            if (result.next()) {
                return result.getInt("nbr");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
