import { useNavigate, Link, NavLink } from 'react-router-dom'
import { useAuth } from '../contexts/AuthContext.jsx'

function Layout({ children }) {
  const { usuario, fazerLogout } = useAuth()
  const navegar = useNavigate()

  function handleLogout() {
    fazerLogout()
    navegar('/login')
  }

  const navClassName = ({ isActive }) =>
    `sidebar-link${isActive ? ' active' : ''}`

  return (
    <div className="app-layout">
      <aside className="sidebar-imobfiscal">
        <Link to="/dashboard" className="sidebar-brand text-decoration-none">
            <i className="bi bi-building me-2" style={{ color: 'var(--cor3)' }}></i>
          <span>ImobFiscal</span>
        </Link>

        <nav className="sidebar-nav" aria-label="Menu principal">
          <NavLink to="/dashboard" className={navClassName}>
            <i className="bi bi-speedometer2"></i><span>Dashboard</span>
          </NavLink>
          <NavLink to="/imoveis" className={navClassName}>
            <i className="bi bi-house-door"></i><span>Imóveis</span>
          </NavLink>
          <NavLink to="/locadores" className={navClassName}>
            <i className="bi bi-person-lines-fill"></i><span>Locadores</span>
          </NavLink>
          <NavLink to="/contratos" className={navClassName}>
            <i className="bi bi-file-text"></i><span>Contratos</span>
          </NavLink>
          <NavLink to="/simulador-fiscal" className={navClassName}>
            <i className="bi bi-calculator"></i><span>Simulador</span>
          </NavLink>
          <NavLink to="/boletos" className={navClassName}>
            <i className="bi bi-receipt"></i><span>Boletos</span>
          </NavLink>
          {['ADMIN', 'GERENTE'].includes(usuario?.perfil) && (
            <NavLink to="/auditoria" className={navClassName}>
              <i className="bi bi-clock-history"></i><span>Auditoria</span>
            </NavLink>
          )}
        </nav>

        <div className="sidebar-footer">
          <span className="sidebar-usuario" title={usuario?.email}>
            <i className="bi bi-person-circle"></i>
            <span>{usuario?.email}</span>
          </span>
          <button className="sidebar-logout" onClick={handleLogout}>
            <i className="bi bi-box-arrow-right"></i>
            <span>Sair</span>
          </button>
        </div>
      </aside>

      <main className="layout-main">
        {children}
      </main>
    </div>
  )
}

export default Layout
