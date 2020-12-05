package uem.co.mz.helpdesk.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import uem.co.mz.helpdesk.dao.ClienteDao;
import uem.co.mz.helpdesk.model.Cliente;
import uem.co.mz.helpdesk.model.TipoCliente;
import uem.co.mz.helpdesk.model.UnidadeOrganica;

@Repository
public class ClienteDaoImpl extends  GenericDaoImpl<Cliente> implements ClienteDao{

	public ClienteDaoImpl( ) {
		super(Cliente.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Cliente> buscarClientes() {
		// TODO Auto-generated method stub
		Query query = getCurrentSession().createQuery("SELECT c FROM Cliente c left join fetch c.sector s left join fetch s.departamento d left join fetch d.unidadeOrganica uo "
				+ " join fetch c.tipoCliente tip order by c.nome asc ");
		return query.list();
	}

	@Override
	public List<Cliente> buscarClientesPorTipo(TipoCliente tipoCliente) {
		// TODO Auto-generated method stub
		Query query = getCurrentSession().createQuery("SELECT c FROM Cliente c left join fetch c.sector s left join fetch s.departamento d left join fetch d.unidadeOrganica uo "
				+ " join fetch c.tipoCliente tip where tip=:tipoCliente order by c.nome asc ");
		query.setParameter("tipoCliente", tipoCliente);
		return query.list();
	}
	
	@Override
	public List<Cliente> buscarClientesPorNomeETipo(String nome, TipoCliente tipoCliente) {
		// TODO Auto-generated method stub
		String parmNome = (nome=="" || nome==null || nome.isEmpty()) ? "" : "";
		String parmTipo = (tipoCliente==null) ? "" : " and tip=:tipoCliente ";
		Query query = getCurrentSession().createQuery("SELECT c FROM Cliente c left join fetch c.sector s left join fetch s.departamento d left join fetch d.unidadeOrganica uo "
				+ " join fetch c.tipoCliente tip where c.nome like :nome "+parmTipo+" order by c.nome asc ");
		query.setParameter("nome", "%"+nome+"%");
		if(tipoCliente!=null){query.setParameter("tipoCliente", tipoCliente);}
		return query.list();
	}	

	@Override
	public List<Cliente> buscarClientesExternos() {
		// TODO Auto-generated method stub
		Query query = getCurrentSession().createQuery("SELECT c FROM Cliente c left join fetch c.sector s left join fetch s.departamento d left join fetch d.unidadeOrganica uo "
				+ " join fetch c.tipoCliente tip where c.sector is null order by c.nome asc ");
		return query.list();
	}

	@Override
	public List<Cliente> buscarClientesPorUnidadeOrganica( UnidadeOrganica unidadeOrganica) {
		// TODO Auto-generated method stub
		Query query = getCurrentSession().createQuery("SELECT c FROM Cliente c join fetch c.sector s join fetch s.departamento d join fetch d.unidadeOrganica uo "
				+ " join fetch c.tipoCliente tip where u=:unidadeOrganica order by c.nome asc ");
		query.setParameter("unidadeOrganica", unidadeOrganica);
		return query.list();
	}
	
}
