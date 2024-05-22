import Container from 'react-bootstrap/Container'
import Nav from 'react-bootstrap/Nav'
import { Navbar } from 'react-bootstrap'
import { Link } from 'react-router-dom'

export function Navigation() {
  return (
    <Navbar bg="light" data-bs-theme="light">
      <Navbar.Brand as={Link} to={'/'}>
        <img
          src="/osta.svg"
          width="40"
          height="40"
          className="d-inline-block align-top ms-2"
          alt="React Bootstrap logo"
        />
        <Navbar.Text className="d-inline-block align-top ms-2 fw-bold">OSTA</Navbar.Text>
      </Navbar.Brand>
      <Container>
        <Nav className="mr-auto">
          <Nav.Link as={Link} to={'/'}>Home</Nav.Link>
          <Nav.Link as={Link} to={'/extension'}>Extensions</Nav.Link>
          <Nav.Link as={Link} to={'/about'}>About</Nav.Link>
        </Nav>
      </Container>
    </Navbar>
  )
}
