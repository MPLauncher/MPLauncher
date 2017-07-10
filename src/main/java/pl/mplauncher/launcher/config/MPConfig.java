/*
   Copyright 2017 MPLauncher Team

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
package pl.mplauncher.launcher.config;

import org.diorite.config.Config;
import org.diorite.config.annotations.Footer;
import org.diorite.config.annotations.Header;

@Header({
                "Welcome in MPLauncher main configuration file!",
                "In this file, you will find every option needed to configure our launcher.",
                "",
                "Good luck, have fun! ~MPLauncher Team."
})
@Footer({
                "Our websites: https://mplauncher.pl / https://github.com/MPLauncher/",
                "",
                "Copyright 2017 MPLauncher Team. Licensed under the Apache License, Version 2.0."
})
public interface MPConfig extends Config {

    // TODO: locale

}
