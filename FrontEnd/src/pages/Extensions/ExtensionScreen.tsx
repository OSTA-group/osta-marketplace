import Container from 'react-bootstrap/Container'
import { Form, InputGroup, ListGroup, Row } from 'react-bootstrap'
import { MarketplaceExtension } from '../../types'
import { useExtensions } from '../../hooks/useExtensions.ts'
import React from 'react'
import { Link } from 'react-router-dom'
import ExtensionCard from '../../components/ExtensionCard.tsx'

export function ExtensionScreen() {

  const [searchName, setSearchName] = React.useState<string>('')
  const { extensions, isGettingExtensions, isErrorGettingExtensions } = useExtensions(searchName)

  return (
    <Container className="my-4">
      <h2>Browse extensions all around the world</h2>
      <Form>
        <InputGroup>
          <InputGroup.Text><i className="bi-search" /></InputGroup.Text>
          <Form.Control
            placeholder="Search by name"
            value={searchName}
            onChange={(e) => {
              setSearchName(String(e.target.value))
            }}
          />
        </InputGroup>
      </Form>

      {extensions && extensions.length > 0 ? (
        <ListGroup>
          <Row>
            {extensions.map((extension: MarketplaceExtension) => (
              <Link key={extension.name} to={`/extension/${extension.id}`} className="text-decoration-none mt-2">
                <ExtensionCard extension={extension} />
              </Link>
            ))}
          </Row>
        </ListGroup>
      ) : (
        <p>No sources found</p>
      )}

      {isGettingExtensions || !extensions && (
        <p className={'glow'}>Loading extensions ...</p>
      )}

      {isErrorGettingExtensions && (
        <p>Something went wrong while loading extensions</p>
      )}

    </Container>
  )
}
