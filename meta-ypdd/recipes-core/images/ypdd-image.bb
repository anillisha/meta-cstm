DESCRIPTION = " A core image for yocto project"
LICENSE = "MIT"

IMAGE_INSTALL = "packagegroup-core-boot"
inherit core-image
# inherit packagegroup
inherit task-info
# DISTRO_FEATURES:append = " systemd"
# VIRTUAL-RUNTIME_init_manager = " systemd"
# DISTRO_FEATURES_BACKFILL_CONSIDERED += " sysvinit"
# VIRTUAL-RUNTIME_initscripts = " systemd-compat-units"

# DISTRO_FEATURES:append = " systemd usrmerge x11"
# # DISTRO_FEATURES:append = " " 
# DISTRO_FEATURES_BACKFILL_CONSIDERED += "sysvinit"
# VIRTUAL-RUNTIME_init_manager = "systemd"
# VIRTUAL-RUNTIME_initscripts = "systemd-compat-units"

# DISTRO_FEATURES_BACKFILL_CONSIDERED += "sysvinit" 
# DISTRO_FEATURES:append = "systemd usrmerge"' 

# VIRTUAL-RUNTIME_init_manager = "systemd"
# VIRTUAL-RUNTIME_initscript = "systemd-compat-units"

# DISTRO_FEATURES:append = " systemd usrmerge"
# DISTRO_FEATURES_BACKFILL_CONSIDERED:append = " sysvinit"
# VIRTUAL-RUNTIME_init_manager ??= "systemd"
# VIRTUAL-RUNTIME_initscripts ??= "systemd-compat-units"

DISTRO_FEATURES:append = " systemd usrmerge"
DISTRO_FEATURES_BACKFILL_CONSIDERED:append = " sysvinit"
VIRTUAL-RUNTIME_init_manager ??= "systemd"
VIRTUAL-RUNTIME_initscripts ??= "systemd-compat-units"
VIRTUAL-RUNTIME_login_manager ??= "shadow-base"
VIRTUAL-RUNTIME_dev_manager ??= "systemd"
# systemd hardcodes /root in its source codes, other values are not offically supported
ROOT_HOME ?= "/root"

#IMAGE_INSTALL +="psplash dropbear lsusbutils"
IMAGE_INSTALL += " dropbear usbutils"
IMAGE_INSTALL += " helloworld"
IMAGE_INSTALL += " autoboot-example"
#IMAGE_INSTALL:append= " rprovides"
#IMAGE_INSTALL:append= " autoboot-example"


python do_display_banner() {
    bb.plain("***********************************************");
    bb.plain("*                                             *");
    bb.plain("*  Example recipe created by bitbake-layers   *");
    bb.plain("*                                             *");
    bb.plain("***********************************************");
}
# IMAGE_INSTALL_append += " helloworld"
addtask display_banner before do_build

IMAGE_ROOTFS_SIZE ?= "9000"


TEST ?= "foo"
TEST := "bar"
