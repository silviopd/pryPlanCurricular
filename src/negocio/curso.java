package negocio;

import datos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class curso extends Conexion{

    private int nro;
    private String nro_curso;
    private int ciclo;
    private String tipo;
    private String nombre;
    private int credito;
    private int nro_prerequisito;
    private String pre_requisitos;

    private ArrayList<curso> cursosDetalle = new ArrayList<curso>();
    
    public int getNro() {
        return nro;
    }

    public void setNro(int nro) {
        this.nro = nro;
    }

    public String getNro_curso() {
        return nro_curso;
    }

    public void setNro_curso(String nro_curso) {
        this.nro_curso = nro_curso;
    }

    public int getCiclo() {
        return ciclo;
    }

    public void setCiclo(int ciclo) {
        this.ciclo = ciclo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCredito() {
        return credito;
    }

    public void setCredito(int credito) {
        this.credito = credito;
    }

    public int getNro_prerequisito() {
        return nro_prerequisito;
    }

    public void setNro_prerequisito(int nro_prerequisito) {
        this.nro_prerequisito = nro_prerequisito;
    }

    public String getPre_requisitos() {
        return pre_requisitos;
    }

    public void setPre_requisitos(String pre_requisitos) {
        this.pre_requisitos = pre_requisitos;
    }

    public ArrayList<curso> getCursosDetalle() {
        return cursosDetalle;
    }

    public void setCursosDetalle(ArrayList<curso> cursosDetalle) {
        this.cursosDetalle = cursosDetalle;
    }
    
    public boolean grabarCompra() throws Exception {
        String sql = "select * from f_generar_correlativo('curso') as numero";
        ResultSet resultado = this.ejecutarSQLSelect(sql);

        if (resultado.next()) {
            int nuevoCodigo = resultado.getInt("numero");
            setNro(nuevoCodigo);

            Connection transaccion = abrirConexion();
            transaccion.setAutoCommit(false);
            
            sql = "INSERT INTO cursos(nro, nro_curso, ciclo, tipo, nombre, credito, nro_prerequisito,pre_requisitos) VALUES (?, ?, ?, ?, ?, ?, ?,?);";
            PreparedStatement sentenciaCurso = transaccion.prepareStatement(sql);
            sentenciaCurso.setInt(1, this.getNro());
            sentenciaCurso.setString(2, this.getNro_curso());
            sentenciaCurso.setInt(3, this.getCiclo());
            sentenciaCurso.setString(4, this.getTipo());
            sentenciaCurso.setString(5, this.getNombre());
            sentenciaCurso.setInt(6, this.getCredito());
            sentenciaCurso.setInt(7, this.getNro_prerequisito());
            sentenciaCurso.setString(8, this.getPre_requisitos());
        }
        return true;
    }
    
    
}
