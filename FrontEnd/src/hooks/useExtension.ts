import { useQuery } from '@tanstack/react-query'
import MarketplaceService from '../services/MarketplaceService.ts'

export function useExtension(extensionUuid: string) {

  // Get extension Details
  const {
    isPending: isGettingExtensionDetails,
    isError: isErrorGettingExtensionDetails,
    data: extensionDetails,
  } = useQuery({
    queryKey: ['extension_details', extensionUuid],
    queryFn: () => MarketplaceService.getExtensionById(extensionUuid),
  })

  return {
    // Get extension Details
    isGettingExtensionDetails,
    isErrorGettingExtensionDetails,
    extensionDetails,

  }
}
