import { Navigation } from './components/Navigation.tsx'
import 'bootstrap/dist/css/bootstrap.min.css'
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import { HomeScreen } from './pages/HomeScreen.tsx'
import { AboutScreen } from './pages/AboutScreen.tsx'
import axios from 'axios'
import { MutationCache, QueryCache, QueryClient, QueryClientProvider } from '@tanstack/react-query'
import { ExtensionScreen } from './pages/Extensions/ExtensionScreen.tsx'
import { ExtensionDetailScreen } from './pages/Extensions/ExtensionDetailScreen.tsx'
import 'bootstrap-icons/font/bootstrap-icons.css'
import '../src/components/css/fonts/index.css'
import '../src/components/css/effect.css'
import { Footer } from './components/Footer.tsx'

function App() {

  const queryClient = new QueryClient({
    queryCache: new QueryCache(),
    mutationCache: new MutationCache(),
  })

  axios.defaults.baseURL = 'https://osta.bauwendr.com'

  return (
    <>
      <QueryClientProvider client={queryClient}>
        <BrowserRouter>
          <div className={'d-flex flex-column vh-100'}>
            <Navigation />
            <div className={'flex-grow-1'}>
              <Routes>
                <Route path="/" element={<HomeScreen />} />
                <Route path="/about" element={<AboutScreen />} />
                <Route path="/extension" element={<ExtensionScreen />} />
                <Route path="/extension/:extensionId" element={<ExtensionDetailScreen />} />
              </Routes>
            </div>
            <Footer />
          </div>
        </BrowserRouter>
      </QueryClientProvider>
    </>
  )
}

export default App
