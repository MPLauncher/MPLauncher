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
package pl.mplauncher.launcher.logging;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.Logger;

import java.io.IOException;
import java.io.OutputStream;

public final class Log4j2OutputStream extends OutputStream {

    private final Logger logger;
    private final Level level;
    private String prefix;

    public Log4j2OutputStream(Logger logger, Level level) {
        this(logger, level, null);
    }

    public Log4j2OutputStream(Logger logger, Level level, String prefix) {
        Validate.isTrue(logger != null, "Logger can not be null!");
        Validate.isTrue(level != null, "Level can not be null!");

        this.logger = logger;
        this.level = level;
        this.prefix = prefix;
    }

    @Override
    public void write(int b) throws IOException {
        write(String.valueOf((char) b));
    }

    @Override
    public void write(byte[] b) throws IOException {
        write(new String(b));
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        write(new String(b, off, len));
    }

    private void write(String string) {
        string = StringUtils.trim(string);
        if (string.isEmpty()) {
            return;
        }

        synchronized (this.logger) {
            if (prefix == null)
                this.logger.log(level, string);
            else
                this.logger.log(level, prefix + string);
        }
    }

}
