 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import RN.UsuarioRNLocal;
import entidad.Usuario;
import entidad.tipoUsuario;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Properties;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.component.commandbutton.CommandButton;
import recursos.Encriptacion;

/*
 import java.util.Properties;
 import javax.mail.Message;
 import javax.mail.MessagingException;
 import javax.mail.PasswordAuthentication;
 import javax.mail.Session;
 import javax.mail.Transport;
 import javax.mail.internet.InternetAddress;
 import javax.mail.internet.MimeMessage; 
 */
/**
 *
 * @author RafaTrossero
 */
@ManagedBean(name = "loginBean")
@RequestScoped
public class LoginBean {
    private Encriptacion encript = new Encriptacion();
    private String user;
    private String password;
    private String email;
    private String receptor;
    private String passwordNueva;
    private String num;

    private CommandButton cbAction;
    private int iActionBtnSelect;
    // private Email email;

    private Usuario usuarioValidado;
    private Usuario usuarioContraseña;
    private SecureRandom random = new SecureRandom();

    @ManagedProperty(value = "#{usuarioLogerBean}")
    private UsuarioLogerBean usuarioLogerBean;

//       
    @ManagedProperty("#{sessionNews}")
    private SessionNews sessionNews;
//    @ManagedProperty(value="#{sessionControlerBean}")
//    private SessionControlerBean sessionControlerBean;
//    
    @EJB
    private UsuarioRNLocal usuarioRNLocal;

    public LoginBean() {

    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReceptor() {
        return receptor;
    }

    public void setReceptor(String receptor) {
        this.receptor = receptor;
    }

    public String getPasswordNueva() {
        return passwordNueva;
    }

    public void setPasswordNueva(String passwordNueva) {
        this.passwordNueva = passwordNueva;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public Usuario getUsuarioValidado() {
        return usuarioValidado;
    }

    public void setUsuarioValidado(Usuario usuarioValidado) {
        this.usuarioValidado = usuarioValidado;
    }

    public Usuario getUsuarioContraseña() {
        return usuarioContraseña;
    }

    public void setUsuarioContraseña(Usuario usuarioContraseña) {
        this.usuarioContraseña = usuarioContraseña;
    }

    public UsuarioLogerBean getUsuarioLogerBean() {
        return usuarioLogerBean;
    }

    public void setUsuarioLogerBean(UsuarioLogerBean usuarioLogerBean) {
        this.usuarioLogerBean = usuarioLogerBean;
    }

    public SessionNews getSessionNews() {
        return sessionNews;
    }

    public void setSessionNews(SessionNews sessionNews) {
        this.sessionNews = sessionNews;
    }

    public UsuarioRNLocal getUsuarioRNLocal() {
        return usuarioRNLocal;
    }

    public void setUsuarioRNLocal(UsuarioRNLocal usuarioRNLocal) {
        this.usuarioRNLocal = usuarioRNLocal;
    }

    public CommandButton getCbAction() {
        return cbAction;
    }

    public void setCbAction(CommandButton cbAction) {
        this.cbAction = cbAction;
    }

    public int getiActionBtnSelect() {
        return iActionBtnSelect;
    }

    public void setiActionBtnSelect(int iActionBtnSelect) {
        this.iActionBtnSelect = iActionBtnSelect;
    }

    public void actionBtn() throws Exception {

        switch (this.getiActionBtnSelect()) {
            case 0:
                create();
                //limíar campos
                //this.limpiar();
                break;
            case 1:
                // this.edit();
                break;
            case 2:
                //deshabilita el campo
                // this.activate(Boolean.FALSE);
                break;
            case 3:
                //habilita el campo
                //this.activate(Boolean.TRUE);
                break;

        }//fin switch
    }//fin actionBtn

    public void setBtnSelect(ActionEvent e) {
        CommandButton btnSelect = (CommandButton) e.getSource();
        System.out.println("boton select: " + btnSelect.getId());
    //0 crea
        //1: edit
        //2 delete

        //activo el boton
        this.getCbAction().setDisabled(false);

        if (btnSelect.getId().equals("cbNuevo")) {
            this.getCbAction().setValue("Guardar");
            this.setiActionBtnSelect(0);

            //campos requeridos
            //this.setbCamposRequeridos(true);
        }
    }//fin setBtnSelect

    public void create() throws Exception {

        String sMensaje = "";
        FacesMessage fm;
        FacesMessage.Severity severity = null;
        try {

            System.out.println("entroal create");
            usuarioValidado = usuarioRNLocal.findByUsuarioContrasena(this.getUser(), encript.hash(this.getPassword()));
           
            System.out.println("password que busco" + encript.hash(this.getPassword()));
            System.out.println("usuario ESSSS" + usuarioValidado);
            System.out.println("password ACTUAL" + usuarioValidado.getPassword());
            System.out.println("password del objeto" + encript.hash(this.getPassword()));

            if (usuarioValidado.getPassword().equals(encript.hash(this.getPassword()))) {
                System.out.println("entro al if");
                System.out.println("password vieja" + usuarioValidado.getPassword());
                usuarioValidado.setPassword( encript.hash(this.getPasswordNueva()));
                System.out.println("nueva" + usuarioValidado.getPassword());

                usuarioRNLocal.edit(usuarioValidado);

            } else {
                System.out.println("entro al else");
                sMensaje = "Contraseña Incorrecta";
                severity = FacesMessage.SEVERITY_ERROR;
                return;
            }
            sMensaje = "La contraseña fue cambiada exitosamente";
            severity = FacesMessage.SEVERITY_INFO;
        } catch (Exception ex) {
            sMensaje = "Usuario o Contraseña Incorrecto";
            severity = FacesMessage.SEVERITY_ERROR;

        } finally {
            fm = new FacesMessage(severity, sMensaje, null);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
        }
    }// fin crear

    public String login() {

        try {
//            Users usuAux = usersRNLocal.findByUsuarioContrasena(user, Encrypter.encriptar(password));
            System.out.println("ingreso :" + user +"      " + password );
            String encriptMD5= encript.hash(password);
            
            usuarioValidado = usuarioRNLocal.findByUsuarioContrasena(user, encriptMD5);
            System.out.println("el usuariovalidado es" +usuarioValidado );

            // this.getSessionNews().setNews(new News());
            if (usuarioValidado != null) {
                
                //que hace esto??
                HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

                usuarioLogerBean.setUsuario(usuarioValidado);
                System.out.println("el usuario validado es:" + usuarioValidado.getUsername() + usuarioValidado.getTipousuario());
               
                if (usuarioValidado.getTipousuario().equals(tipoUsuario.ADMINISTRADOR)) {

                    usuarioLogerBean.setAdministracion(Boolean.TRUE);
                    usuarioLogerBean.setEmpaque(Boolean.TRUE);
                    return "index.xhtml?faces-redirect=true";
                }
                if (usuarioValidado.getTipousuario().equals(tipoUsuario.EMPAQUE)) {
                    System.out.println("entro al empaque" + usuarioValidado.getTipousuario());
                    usuarioLogerBean.setAdministracion(Boolean.FALSE);
                    usuarioLogerBean.setEmpaque(Boolean.TRUE);
                    return "index.xhtml?faces-redirect=true";
                }
                

//                session.setAttribute("user_validated", userValidated);
             /*   if (userValidated.getUserType().equals(UserType.STUDENT)
                 || userValidated.getUserType().equals(UserType.TEACHER)) {
                 usersLogerBean.setbUsers(Boolean.FALSE);
                 usersLogerBean.setbCuiaUser(Boolean.FALSE);
                 usersLogerBean.setbHasWorkExperience(Boolean.TRUE);
                 usersLogerBean.setbHasResearchExperience(Boolean.TRUE);
                 usersLogerBean.setbProject(Boolean.TRUE);
                 usersLogerBean.setbExchangeProgramaCreateUpdate(Boolean.FALSE);
                 if (userValidated.getUrlImage().equals("withoutphoto.png")) {
                 usersLogerBean.setbHasImage(Boolean.FALSE);

                 } else {
                 usersLogerBean.setbHasImage(Boolean.TRUE);
                 }
                 System.out.println("tiene imagen" + usersLogerBean.getbHasImage());
                 //Aqui se deberia seguir agregando los permisos que TEACHER
                 //y STUDENT tienen en COMUN.
                 return "Admin/index.xhtml?faces-redirect=true";
                 }*/
                /*
                 if (userValidated.getUserType().equals(UserType.UNIVERSITY_ADMINISTRATOR)) {
                 usersLogerBean.setbUsers(Boolean.TRUE);
                 usersLogerBean.setbCuiaUser(Boolean.FALSE);
                 usersLogerBean.setbHasWorkExperience(Boolean.FALSE);
                 usersLogerBean.setbHasResearchExperience(Boolean.FALSE);
                 usersLogerBean.setbExchangeProgramaCreateUpdate(Boolean.TRUE);
                 if (userValidated.getUrlImage().equals("withoutphoto.png")) {
                 usersLogerBean.setbHasImage(Boolean.FALSE);

                 } else {
                 usersLogerBean.setbHasImage(Boolean.TRUE);
                 }

                 //Aqui se deberia seguir agregando los permisos del ADMINISTRADOR.
                 return "Admin/index.xhtml?faces-redirect=true";
                 } else if (userValidated.getUserType().equals(UserType.CUIA)) {
                 usersLogerBean.setbExchangeProgramaCreateUpdate(Boolean.TRUE);
                 usersLogerBean.setbCuiaUser(Boolean.TRUE);
                 usersLogerBean.setbUsers(Boolean.FALSE);
                 usersLogerBean.setbHasWorkExperience(Boolean.FALSE);
                 usersLogerBean.setbHasResearchExperience(Boolean.FALSE);
                 usersLogerBean.setbExchangeProgramaCreateUpdate(Boolean.TRUE);
                 if (userValidated.getUrlImage().equals("withoutphoto.png")) {
                 usersLogerBean.setbHasImage(Boolean.FALSE);

                 } else {
                 usersLogerBean.setbHasImage(Boolean.TRUE);
                 }
                 return "Admin/index.xhtml?faces-redirect=true";
                 }*/
            } else {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario o contraseña invalidos", null);
                FacesContext fc = FacesContext.getCurrentInstance();
                fc.addMessage(null, fm);
            }//fin else

//            System.out.println("user: " + this.getUser() + " password: " + this.getPassword());
//            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
//            request.login(this.getUser(), this.getPassword());
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error en log in " + e.getMessage(), null);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
            //      FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid credentials, try again", ""));
        }

        return "";

    }//fin login

    public void logout() throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        request.getSession().invalidate();
        request.logout();
        FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
    } //fin logout

    public String getLoggedUser() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        if (request.getUserPrincipal() != null) {
            return request.getUserPrincipal().getName();
        }
        return "";
    }//fin getLoggedUser()

    public void recuperarContraseña() throws Exception {
        String sMensaje = "";
        FacesMessage fm;
        FacesMessage.Severity severity = null;
        
        try {
            
            usuarioContraseña = usuarioRNLocal.findByUsuarioEmail(this.getEmail());
            System.out.println("usuarioContraseña ESSSS" + usuarioContraseña);

            System.out.println("password es" + usuarioContraseña.getPassword());
                
            
            num = this.cambiarContraseña();
            usuarioContraseña.setPassword(encript.hash(num));
            usuarioRNLocal.edit(usuarioContraseña);

            System.out.println("  nueva password ESS " + usuarioContraseña.getPassword());

            receptor = this.getEmail();

            EnviarMsj(num, receptor);
            
            sMensaje = "La nueva Contraseña se ha enviado a su Email";
            severity = FacesMessage.SEVERITY_INFO;
        
        
           
        }
    
         catch (Exception ex) {

            sMensaje = "El Email Ingresado es Incorrecto";
            severity = FacesMessage.SEVERITY_ERROR;

        } finally {
            fm = new FacesMessage(severity, sMensaje, null);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);

        }
    }

    public void EnviarMsj(String msj, String receptor) {
        
        try {
            // Propiedades de la conexión
            System.out.println("DURANTE de enviar mail");

            Properties props = new Properties();
            props.setProperty("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.user", "departamentoinformaticaunca@gmail.com");
            props.setProperty("mail.smtp.auth", "true");

            // Preparamos la sesion
            Session session = Session.getDefaultInstance(props);

            // Construimos el mensaje
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("departamentoinformaticaunca@gmail.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(receptor));
            message.setSubject("Departamento Informatica");
            message.setText("Hola " + usuarioContraseña.getUsername() + ", tu nueva contraseña es  " + msj + " , te recomendamos que ingreses al sistema y cambies la clave. Atte Departamento de Informatica. Saludos.");

            // Lo enviamos.
            Transport t = session.getTransport("smtp");
            t.connect("departamentoinformaticaunca@gmail.com", "LabUnca2011");
            t.sendMessage(message, message.getAllRecipients());

            // Cierre.
            t.close();
           
        }   catch (Exception e)
        {
            e.printStackTrace();
        }
        System.out.println("DESPUES de enviar mail");
    }

    public String cambiarContraseña() throws Exception {

       return new BigInteger(50, random).toString(16);

    }

    /*
     public  void EnviarMsj() {
            
     Properties props = new Properties();
     props.put("mail.smtp.host", "smtp.gmail.com");
     props.put("mail.smtp.socketFactory.port", "465");
     props.put("mail.smtp.socketFactory.class",
     "javax.net.ssl.SSLSocketFactory");
     props.put("mail.smtp.auth", "true");
     props.put("mail.smtp.port", "465");
 
     Session session = Session.getDefaultInstance(props,
     new javax.mail.Authenticator() {
     @Override
     protected PasswordAuthentication getPasswordAuthentication() {
     return new PasswordAuthentication("Admin","123");
     }
     });
 
     try {
 
     Message message = new MimeMessage(session);
     message.setFrom(new InternetAddress("walterwrv290888@gmail.com"));
     message.setRecipients(Message.RecipientType.TO,
     InternetAddress.parse("walterwrv290888@gmail.com"));
     message.setSubject("Testing Subject");
     message.setText("Dear Mail Crawler," +
     "\n\n No spam to my email, please!");
 
     Transport.send(message);
 
     System.out.println("Done");
 
     } catch (MessagingException e) {
     throw new RuntimeException(e);
     }
     }


     */
}
