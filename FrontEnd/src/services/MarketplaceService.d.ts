/**
 * @packageDocumentation
 * Service to interact with marketplace extensions.
 */

/**
 * Retrieves a list of marketplace extensions based on a provided name.
 *
 * @param {string} name The name to search for.
 * @returns {Promise<MarketplaceExtension[]>} A promise that resolves to an array of `MarketplaceExtension` objects.
 * @throws Error Will throw an error if the extension data cannot be retrieved.
 */
declare async function getExtensions(name: string): Promise<MarketplaceExtension[]>

/**
 * Retrieves detailed information about a specific extension by its ID.
 *
 * @param {string} id The ID of the extension.
 * @returns {Promise<MarketPlaceExtensionDetails>} A promise that resolves to a `MarketPlaceExtensionDetails` object.
 * @throws Error Will throw an error if the extension details cannot be retrieved.
 */
declare async function getExtensionById(id: string): Promise<MarketPlaceExtensionDetails>
