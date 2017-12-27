package com.reizx.andrutil;

import java.io.FileDescriptor;

/**
 * Utility methods for creating and managing a subprocess.
 * <p/>
 * Note: The native methods access a package-private java.io.FileDescriptor
 * field to get and set the raw Linux file descriptor. This might break if the
 * implementation of java.io.FileDescriptor is changed.
 * Created by kig on 2017/12/27.
 */
public class NativeShell {
    static {
        java.lang.System.loadLibrary("andrutil_exec");
    }

    /**
     * Close a given file descriptor.
     */
    public static native void close(FileDescriptor fd);

    /**
     * Create a subprocess. Differs from java.lang.ProcessBuilder in that a pty
     * is used to communicate with the subprocess.
     * <p/>
     * Callers are responsible for calling Exec.close() on the returned file
     * descriptor.
     *
     * @param rdt       Whether redirect stdout and stderr
     * @param cmd       The command to execute.
     * @param args      An array of arguments to the command.
     * @param envVars   An array of strings of the form "VAR=value" to be added to the
     *                  environment of the process.
     * @param scripts   The scripts to execute.
     * @param processId A one-element array to which the process ID of the started
     *                  process will be written.
     * @return File descriptor
     */
    public static native FileDescriptor createSubprocess(int rdt, String cmd,
                                                         String[] args, String[] envVars,
                                                         String scripts, int[] processId);

    /**
     * Send SIGHUP to a process group.
     */
    public static native void hangupProcessGroup(int processId);

    /**
     * Causes the calling thread to wait for the process associated with the
     * receiver to finish executing.
     *
     * @return The exit value of the Process being waited on
     */
    public static native int waitFor(int processId);

}
