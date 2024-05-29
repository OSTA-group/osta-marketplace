import Container from 'react-bootstrap/Container'

export function Footer() {
  return (
    <footer className="footer">
      <Container className="text-center">
        <p>
          &copy; {new Date().getFullYear()} Open Source Travel App (OSTA) - <a href={'/privacy'}>Privacy Policy</a>
        </p>
      </Container>
    </footer>
  )
}
