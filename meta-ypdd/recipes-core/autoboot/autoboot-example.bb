# DESCRIPTON = "Startup scripts"
# LICENSE = "MIT"

# LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
# # Recipe revision - don't forget to 'bump' when a new revision is created !
# #PR = "r0"

# # Runtime dependencies
# #
# # Add a line similar to the following to ensure any packages needed for the scripts to run are installed in the image.
# #
# # RDEPENDS_${PN} = "parted"

# # SRC_URI specifies the files that are to be used as the scripts.
# #
# # Any valid src_uri format can be used - this example assumes the
# # scripts are stored in the 'files' directory below the one in
# # which this file is located.
# #

# inherit systemd    
# SYSTEMD_AUTO_ENABLE = "enable"

# SRC_URI = "              \
#    file://startup-script \
#    file://run-script     \
#    file://support-script \
#    "

# # This function is responsible for:
# #  1) Ensuring the required directories exist in the image;
# #  2) Installing the scripts in to the image;
# #  3) Creating the desired runlevel links to the scripts.
# #
# do_install() {
#     #
#     # Create directories:
#     #   ${D}${sysconfdir}/init.d - will hold the scripts
#     #   ${D}${sysconfdir}/rcS.d  - will contain a link to the script that runs at startup
#     #   ${D}${sysconfdir}/rc5.d  - will contain a link to the script that runs at runlevel=5
#     #   ${D}${sbindir}           - scripts called by the above
#     #
#     # ${D} is effectively the root directory of the target system.
#     # ${D}${sysconfdir} is where system configuration files are to be stored (e.g. /etc).
#     # ${D}${sbindir} is where executable files are to be stored (e.g. /sbin).
#     #
#     install -d ${D}${sysconfdir}/init.d
#     install -d ${D}${sysconfdir}/rcS.d
#     install -d ${D}${sysconfdir}/rc1.d
#     install -d ${D}${sysconfdir}/rc2.d
#     install -d ${D}${sysconfdir}/rc3.d
#     install -d ${D}${sysconfdir}/rc4.d
#     install -d ${D}${sysconfdir}/rc5.d
#     install -d ${D}${sbindir}

#     #
#     # Install files in to the image
#     #
#     # The files fetched via SRC_URI (above) will be in ${WORKDIR}.
#     #
#     install -m 0755 ${WORKDIR}/startup-script  ${D}${sysconfdir}/init.d/
#     install -m 0755 ${WORKDIR}/run-script      ${D}${sysconfdir}/init.d/
#     install -m 0755 ${WORKDIR}/support-script  ${D}${sbindir}/

#     #
#     # Symbolic links can also be installed. e.g.
#     #
#     # ln -s support-script-link ${D}${sbindir}/support-script

#     #
#     # Create symbolic links from the runlevel directories to the script files.
#     # Links of the form S... and K... mean the script when be called when
#     # entering / exiting the runlevel designated by the containing directory.
#     # For example:
#     #   rc5.d/S90run-script will be called (with %1='start') when entering runlevel 5.
#     #   rc5.d/K90run-script will be called (with %1='stop') when exiting runlevel 5.
#     #
#     ln -sf ../init.d/startup-script  ${D}${sysconfdir}/rcS.d/S90startup-script
#     ln -sf ../init.d/run-script      ${D}${sysconfdir}/rc1.d/K90run-script
#     ln -sf ../init.d/run-script      ${D}${sysconfdir}/rc2.d/K90run-script
#     ln -sf ../init.d/run-script      ${D}${sysconfdir}/rc3.d/K90run-script
#     ln -sf ../init.d/run-script      ${D}${sysconfdir}/rc4.d/K90run-script
#     ln -sf ../init.d/run-script      ${D}${sysconfdir}/rc5.d/S90run-script
# }

SUMMARY = "A Example of a recipe that utilizes systemd"
DEESCRIPTION = "Runs sysd.sh script using a systemd service"

LICENSE = "MIT"

LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

SRC_URI = "file://sysd.sh \
           file://sysd.service "

inherit systemd

S = "${WORKDIR}"

RDEPENDS:${PN}  = "bash"

SYSTEMD_AUTO_ENABLE = "enable"
SYSTEMD_SERVICE:${PN} = "sysd.service"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${S}/sysd.sh ${D}${bindir}
    
#    systemd_unitdir = /lib/systemd/
#    sysconfdir = /etc

    install -d ${D}/${sysconfdir}/systemd/system
    install -m 0644 ${S}/sysd.service ${D}/${sysconfdir}/systemd/system
#    install -d ${D}/${systemd_unitdir}/system
#    install -m 0644 ${S}/sysd.service ${D}/${systemd_unitdir}/system
}