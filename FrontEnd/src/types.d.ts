/**
 * Represents a marketplace extension.
 * @property {string} id - Unique identifier of the extension.
 * @property {string} name - Name of the extension.
 * @property {string} type - Type of the extension.
 * @property {string} url - URL of the extension.
 * @property {number} version - Version number of the extension.
 * @property {Record<string, string>} properties - Additional properties of the extension.
 */
export type MarketplaceExtension = {
  id: string
  name: string
  type: string
  url: string
  version: number
  properties: Record<string, string>
}

/**
 * Represents detailed information about a marketplace extension.
 * @property {string} name - Name of the extension.
 * @property {string} description - Description of the extension.
 * @property {string} area - Area where the extension is available.
 * @property {string} type - Type of the extension.
 * @property {string} languages - Language of the extension.
 * @property {Record<string, string>} properties - Additional properties of the extension.
 * @property {Version[]} versions - List of available versions.
 */
export type MarketPlaceExtensionDetails = {
  name: string
  description: string
  area: string
  type: string
  languages: string
  properties: Record<string, string>
  versions: Version[]
}

/**
 * Represents a version of an extension.
 * @property {string} url - URL of the version.
 * @property {number} version - Version number.
 */
export type Version = {
  url: string
  version: number
}
