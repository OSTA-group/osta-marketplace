import { useExtension } from '../../hooks/useExtension.ts'
import { Container, Table } from 'react-bootstrap'
import { useParams } from 'react-router-dom'
import { Version } from '../../types'

export function ExtensionDetailScreen() {

  const { extensionId } = useParams()

  const {
    extensionDetails,
    isGettingExtensionDetails,
    isErrorGettingExtensionDetails,
  } = useExtension(extensionId!)

  if (isGettingExtensionDetails) {
    return (
      <Container><p>Loading extensions</p></Container>
    )
  }

  if (isErrorGettingExtensionDetails || !extensionDetails) {
    return (
      <Container><p>Something went wrong while loading extensions</p></Container>
    )
  }

  return (
    <Container className="my-4">
      <h2>Extension: {extensionDetails.name}</h2>
      <p>Area: {extensionDetails.area}</p>
      <p>Type: {extensionDetails.type}</p>
      <p>{extensionDetails.description}</p>
      {Object.entries(extensionDetails.properties ?? {}).length > 0 && (
        <>
          <h3>Extra Properties</h3>
          <ul>
            {Object.entries(extensionDetails.properties).map(([key, value]) => (
              <li key={key}>
                <strong>{key}: </strong> {value}
              </li>
            ))}
          </ul>
        </>
      )}
      <h3>Version history</h3>
      <Table striped bordered hover>
        <thead>
        <tr>
          <th>URL</th>
          <th>Version</th>
        </tr>
        </thead>
        <tbody>
        {extensionDetails.versions.map((version: Version) => (
          <tr key={version.url}>
            <td>{version.url}</td>
            <td>{version.version}</td>
          </tr>
        ))}
        </tbody>
      </Table>
    </Container>
  )
}