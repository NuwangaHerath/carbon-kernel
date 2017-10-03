/*
 *  Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.wso2.carbon;

import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.context.ConfigurationContextFactory;
import org.testng.annotations.BeforeTest;
import org.wso2.carbon.base.ServerConfiguration;
import org.wso2.carbon.utils.ServerConstants;

import java.io.File;
import java.nio.file.Paths;

/**
 * Base test class shared by test cases in carbon utils component.
 */
public class BaseTest {
    private static final String basedir = Paths.get("").toAbsolutePath().toString();
    protected static final String testDir = Paths.get(basedir, "src", "test", "resources", "carbon-utils").toString();
    protected static final File testSampleDirectory = Paths.get("target", "carbon-utils-test-directory").toFile();

    @BeforeTest
    public void setup() {
        testSampleDirectory.mkdirs();
        System.setProperty(ServerConstants.CARBON_HOME, testDir);
    }

    protected ConfigurationContext createTestConfigurationContext() throws Exception {
        String axis2Repo = Paths.get(testDir, "axis2-repo").toString();
        String serverConfigPath = Paths.get(testDir, "carbon.xml").toString();
        ServerConfiguration.getInstance().forceInit(serverConfigPath);
        ServerConfiguration.getInstance().overrideConfigurationProperty(ServerConfiguration.AXIS2_CONFIG_REPO_LOCATION,
                axis2Repo);
        return ConfigurationContextFactory.
                createConfigurationContextFromFileSystem(axis2Repo, Paths.get(testDir, "axis2.xml").toString());
    }
}
