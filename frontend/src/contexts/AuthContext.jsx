import { createContext, useContext, useState } from 'react'
import { login as loginApi } from '../services/api.js'

const AuthContext = createContext(null)

export function useAuth() {
  return useContext(AuthContext)
}

export function AuthProvider({ children }) {
  const [usuario, setUsuario] = useState(() => {
    const token = localStorage.getItem('imobfiscal_token')
    const email = localStorage.getItem('imobfiscal_email')
    const imobiliariaId = localStorage.getItem('imobfiscal_imobiliaria_id')
    const perfil = localStorage.getItem('imobfiscal_perfil')
    return token && email && imobiliariaId ? { email, imobiliariaId, perfil } : null
  })

  async function fazerLogin(email, senha) {
    const resposta = await loginApi(email, senha)
    const { token, email: emailRetornado, imobiliariaId, perfil } = resposta.data

    localStorage.setItem('imobfiscal_token', token)
    localStorage.setItem('imobfiscal_email', emailRetornado)
    localStorage.setItem('imobfiscal_imobiliaria_id', imobiliariaId)
    localStorage.setItem('imobfiscal_perfil', perfil)

    setUsuario({ email: emailRetornado, imobiliariaId, perfil })
  }

  function fazerLogout() {
    localStorage.removeItem('imobfiscal_token')
    localStorage.removeItem('imobfiscal_email')
    localStorage.removeItem('imobfiscal_imobiliaria_id')
    localStorage.removeItem('imobfiscal_perfil')
    setUsuario(null)
  }

  const valor = {
    usuario,
    fazerLogin,
    fazerLogout,
  }

  return (
    <AuthContext.Provider value={valor}>
      {children}
    </AuthContext.Provider>
  )
}
