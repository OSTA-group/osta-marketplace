import Container from 'react-bootstrap/Container'

export function Footer() {
  return (
    <footer className="footer">
      <Container className="text-center">
        <p>&copy; {new Date().getFullYear()} Open Source Travel App (OSTA)</p>
      </Container>
    </footer>
  )
}