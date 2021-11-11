package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class DAOVenta {
	int npedido;
	String usuario,fecha;
	
	public DAOVenta() {
		
	}
	
	public DAOVenta(int npedido, String usuario, String fecha) {
		this.npedido=npedido;
		this.usuario=usuario;
		this.fecha=fecha;
	}

	public int getNpedido() {
		return npedido;
	}

	public void setNpedido(int npedido) {
		this.npedido = npedido;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	//INTEGRACIÓN CON LA BBDD
	//La clase DAO debe tener conexión con la BBDD para conectarse con su tabla
	private static Statement sentencia;
	private static ResultSet resultado;
	
	public static void setConexionBBDD(Statement sentencia, ResultSet resultado) {
		DAOVenta.sentencia = sentencia;
		DAOVenta.resultado = resultado;
	}
	
	//MÉTODOS CRUD
	//READ
	//Método que extra todos los registros de la tabla
	public static Vector<DAOVenta> obtenerVentas() throws SQLException{
		String sqlQuery = "select * from venta;";
		//Este método devolverá un vector de tipo <AutorVenta>
		return buscaResultadosConConsulta(sqlQuery);	
	}
	public static DAOVenta obtenerVenta(String npedido) throws SQLException{
		String sqlQuery = "select * from venta WHERE npedido='"+npedido+"';";
		return buscaResultadosUnaVenta(sqlQuery);
	}
	//Este método se utiliza para meter datos con la sentencia INSERT
	public static void insertarVenta(int npedido, String usuario, String fecha) {
		try {
			String sqlQuery = "INSERT INTO libreria.venta VALUES ('"+npedido+"','"+usuario+"','"+fecha+"');";
			DAOVenta.sentencia.execute(sqlQuery);
			System.out.println("La venta con el número de pedido "+npedido+" ha sido insertada con éxito.");
		}catch(SQLException e) {
			System.err.println("\nNo se han podido insertar datos en la venta con el código "+npedido);
		}
	}
	
	//Este método ejecutará una sentencia UPDATE para modificar una venta
			public static void modificarVenta(int npedido, String usuario, String fecha){
				try {
					String sqlQuery = "UPDATE libreria.venta SET usuario= '"+usuario+"',fecha= '"+fecha+"' WHERE npedido='"+npedido+"';";
					DAOVenta.sentencia.executeUpdate(sqlQuery);	
					System.out.println("La venta con el número de pedido "+npedido+" ha sido modificada con éxito.");
				}catch(SQLException e) {
					System.err.println("Error al modificar la venta.\n"+e.getStackTrace());
				}
			} 
			
	//Este método ejecutará una sentencia DELETE para eliminar una venta
	public static int borrarVenta(String npedido) throws SQLException {
		String sqlQuery="DELETE FROM libreria.venta WHERE npedido='"+npedido+"'";
		return DAOVenta.sentencia.executeUpdate(sqlQuery);
	}
	//FIN MÉTODOS CRUD
	
	//MÉTODOS UTILITY DE CLASE
	//Método que extrae filas de la tabla a través de una consulta
	private static Vector<DAOVenta> buscaResultadosConConsulta(String consulta) throws SQLException{
		resultado = DAOVenta.sentencia.executeQuery(consulta);
		return cargaResultSetToVector(resultado);

	}
	
	private static Vector<DAOVenta> cargaResultSetToVector(ResultSet resultado) throws SQLException {
		Vector<DAOVenta> ventas = new Vector<DAOVenta>();
		DAOVenta venta;
		
		while(resultado.next()) {
			int npedido = resultado.getInt(1);
			String usuario = resultado.getString(2);
			String fecha= resultado.getString(3);
			venta = new DAOVenta(npedido,usuario,fecha);
			ventas.addElement(venta);
		}
		
		return ventas;
}
	private static DAOVenta buscaResultadosUnaVenta(String consulta) throws SQLException{
		resultado = DAOVenta.sentencia.executeQuery(consulta);
		return cargaResultSetToVenta(resultado);
		
	}
	
	private static DAOVenta cargaResultSetToVenta(ResultSet resultado) throws SQLException {
		
		DAOVenta venta = null;
		
		while(resultado.next()) {
			int npedido = resultado.getInt(1);
			String usuario= resultado.getString(2);
			String fecha = resultado.getString(3);
			venta = new DAOVenta(npedido,usuario,fecha);			
		}
		
		return venta;
	}
	public static void mostrarVenta(DAOVenta venta) {
		System.out.println("El usuario: "+venta.getUsuario()+" hizo una compra el día "+venta.getFecha()+" con el número de pedido: "+venta.getNpedido()+".");


	}
	//FIN MÉTODOS UTILITY DE CLASE

}
