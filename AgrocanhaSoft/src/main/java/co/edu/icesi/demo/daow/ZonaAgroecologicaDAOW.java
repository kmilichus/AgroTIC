package co.edu.icesi.demo.daow;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.demo.mapeo.MapeoCaracteristica;
import co.edu.icesi.demo.modelo.Caracteristica;
import co.edu.icesi.demo.modelo.Familiatextural;
import co.edu.icesi.demo.modelo.Grupohomogeneosuelo;
import co.edu.icesi.demo.modelo.Grupohumedad;
import co.edu.icesi.demo.modelo.Grupotextural;
import co.edu.icesi.demo.modelo.Ingenio;
import co.edu.icesi.demo.modelo.NivelhumTipopermeaGrupohum;
import co.edu.icesi.demo.modelo.Tipocaracteristica;
import co.edu.icesi.demo.modelo.Usuario;
import co.edu.icesi.demo.modelo.Zonaagroecologica;
import co.edu.icesi.demo.modelo.Zonavariedad;

@Repository
@Scope("singleton")
public class ZonaAgroecologicaDAOW implements IZonaAgroecologicaDAOW {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Zonaagroecologica> darTodasLasZonasAgroecologicas() {

		String hql = "SELECT zn FROM Zonaagroecologica zn";
		return sessionFactory.getCurrentSession().createQuery(hql).getResultList();

	}

	@Override
	public Zonaagroecologica darZonaAgroecologica(int grupoSuelo, String grupoHumedad) {

		String hql = "SELECT z FROM Zonaagroecologica z "
		+ "WHERE z.grupohomogeneosuelo.numerogrupo = :grupoSueloNumero "
				+ "AND z.grupohumedad.siglasgrhum = :grupoHumedadCodigo";

		try {
			return (Zonaagroecologica) sessionFactory.getCurrentSession().createQuery(hql)
					.setParameter("grupoSueloNumero", grupoSuelo).setParameter("grupoHumedadCodigo", grupoHumedad)
					.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}

	}

	@Override
	public List<Ingenio> consultarIngenios(Zonaagroecologica nZonaAgroecologica) {

		String hql = "SELECT z.ingenio FROM ZonaagroecologicaIngenio z "
				+ "WHERE z.zonaagroecologica.zonaagroid = :idZona";

		return sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter("idZona", nZonaAgroecologica.getZonaagroid()).getResultList();
	}

	@Override
	public List<Zonavariedad> consultarZonasVariedad(Zonaagroecologica nZonaAgroecologica) {

		String hql = "SELECT z.zonavariedad FROM ZonaagroZonavariedad z "
				+ "WHERE z.zonaagroecologica.zonaagroid = :idZona";

		return sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter("idZona", nZonaAgroecologica.getZonaagroid()).getResultList();

	}

	@Override
	public List<Familiatextural> consultarFamiliasTexturales(Zonaagroecologica nZonaAgroecologica) {

		String hql = "SELECT gf.familiatextural FROM GrupohomosueloFamiliatextural gf "
				+ "WHERE gf.grupohomogeneosuelo.grhomosueloid = :idZona";

		return sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter("idZona", nZonaAgroecologica.getGrupohomogeneosuelo().getGrhomosueloid()).getResultList();
	}

	@Override
	public Zonaagroecologica consultarZonaPorCodigo(String codigoZona) {

		String hql = "SELECT zn FROM Zonaagroecologica zn" + " WHERE zn.codigoznagro = :codigoZona";

		try {
			return (Zonaagroecologica) sessionFactory.getCurrentSession().createQuery(hql)
					.setParameter("codigoZona", codigoZona).getSingleResult();

		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public List<MapeoCaracteristica> consultarCaracteristicasZona(Zonaagroecologica zonaAgroecologica) {

		String hql = "SELECT gC.caracteristica FROM GrupohomosueloCaracteristica gC "
				+ "WHERE gC.grupohomogeneosuelo.grhomosueloid = :idGrupoSueloZona";

		List<Caracteristica> caracteristicas = sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter("idGrupoSueloZona", zonaAgroecologica.getGrupohomogeneosuelo().getGrhomosueloid())
				.getResultList();

		List<MapeoCaracteristica> mapeos = new ArrayList<MapeoCaracteristica>();

		
		for (Caracteristica carActual : caracteristicas) {

			MapeoCaracteristica mapeoActual = new MapeoCaracteristica();

			mapeoActual.setDescripcionCaract(carActual.getDescripcioncaract());

			BigDecimal tipoCaracteristicaID = carActual.getTipocaracteristica().getTipocaractid();

			String subhql = "SELECT tc.nombretipo FROM Tipocaracteristica tc "
						+ "WHERE tc.tipocaractid = :id";

			String nombreTipoCar = (String) sessionFactory.getCurrentSession().createQuery(subhql)
					.setParameter("id", tipoCaracteristicaID).getSingleResult();
			
			mapeoActual.setNombreCaract(nombreTipoCar);

			mapeos.add(mapeoActual);
		}

		return mapeos;

	}

	@Override
	public List<Grupotextural> consultarGruposTexturales(Zonaagroecologica zonaAgroecologica) {

		String hql = "SELECT distinct gf.familiatextural.grupotextural FROM GrupohomosueloFamiliatextural gf "
				+ "WHERE gf.grupohomogeneosuelo.grhomosueloid = :idZona";

		return sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter("idZona", zonaAgroecologica.getGrupohomogeneosuelo().getGrhomosueloid()).getResultList();

	}

	@Override
	public Grupohomogeneosuelo darGrupoHomogeneoSuelo(Zonaagroecologica zonaAgroecologica) {

		String hql = "SELECT z.grupohomogeneosuelo FROM Zonaagroecologica z" + " WHERE z.zonaagroid = :idZona";

		try {

			return (Grupohomogeneosuelo) sessionFactory.getCurrentSession().createQuery(hql)
					.setParameter("idZona", zonaAgroecologica.getZonaagroid()).getSingleResult();

		} catch (NoResultException e) {
			return null;
		}

	}

	@Override
	public Grupohumedad darGrupoHumedad(Zonaagroecologica zonaAgroecologica) {

		String hql = "SELECT z.grupohumedad FROM Zonaagroecologica z" + " WHERE z.zonaagroid = :idZona";

		try {

			return (Grupohumedad) sessionFactory.getCurrentSession().createQuery(hql)
					.setParameter("idZona", zonaAgroecologica.getZonaagroid()).getSingleResult();

		} catch (NoResultException e) {
			return null;
		}

	}

	@Override
	public List<NivelhumTipopermeaGrupohum> darDatosHumedad(Zonaagroecologica zonaAgroecologica) {

		String hql = "SELECT t FROM NivelhumTipopermeaGrupohum t" + " WHERE t.grupohumedad.grupohumid = :idHumedad";

		return sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter("idHumedad", zonaAgroecologica.getGrupohumedad().getGrupohumid()).getResultList();

	}

}
