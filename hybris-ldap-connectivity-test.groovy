import javax.naming.directory.InitialDirContext;
import javax.naming.AuthenticationNotSupportedException;
import javax.naming.NamingException;
import javax.naming.AuthenticationException;
import javax.naming.directory.DirContext;
import javax.naming.Context;
import de.hybris.platform.ldap.connection.ssl.JNDISocketFactory;

//default port for ldap is 389(unsecure) or 636(over ssl)
String ldapServerUrl = "ldap://<your_server_hostname>:<your_server_port>"

Hashtable environment = new Hashtable();
env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.ldapCtxFactory");
env.put(Context.PROVIDER,ldapServerUrl);
env.put(Context.SECURITY_AUTHENTICATION,"simple");
env.put(Context.SECURITY_PRINCIPAL, "cn=sample,o=test,c=test");
env.put(Context.SECURITY_CREDENTIALS, "secret");
env.put(Context.SECURITY_PROTOCOL, "ssl");
env.put("java.naming.ldap.factory.socket","de.hybris.platform.ldap.connection.ssl.JNDISocketFactory");
env.put("com.sun.jndi.ldap.read.timeout","10000");
env.put("com.sun.jndi.ldap.connection.timeout","10000");

try{
JNDISocketFactory.init("<path to cacert for secure connection>",null,null,null,"JKS","JKS");

DirContext ctx = new InitialDirContext(env);
System.out.println("Connected");
System.out.println(ctx.getEnvironment());

}catch(AuthenticationNotSupportedException anse){
    System.out.println("Authentication is not supported by server");

}catch(AuthenticationException ae){
    System.out.println("Invalid credentials");
}finally{
    ctx.close();
}

