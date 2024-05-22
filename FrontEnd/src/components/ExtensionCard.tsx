import { MarketplaceExtension } from '../types'
import { Card, CardText } from 'react-bootstrap'

interface ExtensionCardProps {
  extension: MarketplaceExtension
}

export function ExtensionCard({ extension }: ExtensionCardProps) {
  return (
    <Card body className="shadow">
      <div className="d-flex align-items-center">
        <Card.Img
          src={`https://eu.ui-avatars.com/api/?name=${extension.name}&background=random&rounded=true&size=128`}
          alt="Image description"
          className="card__image__size"
        />
        <CardText className="m-4">{extension.name}</CardText>
        <span className="badge bg-secondary position-absolute top-0 end-0 m-2">{extension.type}</span>
      </div>
    </Card>
  )
}

export default ExtensionCard
