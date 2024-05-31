-- sql file for testing
-- Test extension
INSERT INTO extensions (id, dtype, name, type, area, languages, url_filterable)
values ('00000000-0000-0000-0000-000000000001', 'WebExtension', 'test-web', 'web', 'Global', 'English', false);
INSERT INTO versions (id, url, configuration_url, version, extension_id)
values ('00000000-0000-0000-0000-000000000001', 'http://localhost', null, 1, '00000000-0000-0000-0000-000000000001');

-- Beveren Verbind extension
INSERT INTO extensions (id, dtype, name, type, area, languages, url_filterable)
values ('00000000-0000-0000-0000-000000000002', 'WebExtension', 'test-web2', 'web', 'Belgium', 'Nederlands', true);
INSERT INTO versions (id, url, configuration_url, version, extension_id)
values ('00000000-0000-0000-0000-000000000002', 'http://localhost', null, 1, '00000000-0000-0000-0000-000000000002');

-- Wikipedia extension
INSERT INTO extensions (id, dtype, name, type, area, languages)
values ('00000000-0000-0000-0000-000000000003', 'PythonExtension', 'test-python', 'python', 'Global', 'All');
INSERT INTO versions (id, url, configuration_url, version, file_Hash, extension_id)
values ('00000000-0000-0000-0000-000000000003', 'http://localhost', 'http://localhost', 1, '00000000000000000000000000000000', '00000000-0000-0000-0000-000000000003');

-- Broken extension
INSERT INTO extensions (id, dtype, name, type, area, languages, url_filterable)
values ('00000000-0000-0000-0000-000000000004', 'WebExtension', 'test-broken', 'python', 'None', 'None', false);
