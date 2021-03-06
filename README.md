# Gogradle - a Full-featured Build Tool for Golang

[中文文档](./README_CN.md)

[![Build Status](https://travis-ci.org/blindpirate/gogradle.svg?branch=master)](https://travis-ci.org/blindpirate/gogradle)
[![Build Status](https://ci.appveyor.com/api/projects/status/github/blindpirate/gogradle?branch=master&svg=true)](https://ci.appveyor.com/api/projects/status/github/blindpirate/gogradle?branch=master&svg=true)
[![Coverage Status](https://coveralls.io/repos/github/blindpirate/gogradle/badge.svg?branch=master)](https://coveralls.io/github/blindpirate/gogradle?branch=master)
[![Java 8+](https://img.shields.io/badge/java-8+-4c7e9f.svg)](http://java.oracle.com)
[![Apache License 2](https://img.shields.io/badge/license-APL2-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.txt)

Gogradle is a gradle plugin which provides support for building golang.

> 2017-02-12 Now Gogradle can build 526 of [Github's top 1000 Go repositories](http://github-rank.com/star?language=Go) **WITHOUT** any extra configuration!
>
> 2017-02-26 Now Gogradle can integrate with IDE perfectly!

## Feature

- No need to preinstall anything but `JDK 8+` (including golang itself)
  - If you're using JetBrains IDE, then JDK is not required
- Support all versions of golang and allow their existence at the same time
- Perfect cross-platform support (as long as `Java` can be run, all tests have passed on OS X 10.11/Ubuntu 12.04/Windows 7)
- Project-scope build, needless to set `GOPATH`
- Full-featured package management
  - Needless to install dependency packages manually, all you need to do is specifying the version
  - Four VCS supported without installation: Git/Svn/Mercurial/Bazaar (Currently only Git is implemented)
  - Transitive dependency management
  - Resolve package conflict automatically
  - Support dependency lock
  - Support importing dependencies managed by various external tools such as glide/glock/godep/gom/gopm/govendor/gvt/gbvendor/trash (Based on [this report](https://github.com/blindpirate/report-of-go-package-management-tool))
  - Support [submodule](https://git-scm.com/book/en/v2/Git-Tools-Submodules)
  - Support [SemVersion](http://semver.org/)
  - Support [vendor](https://docs.google.com/document/d/1Bz5-UB7g2uPBdOx-rw5t9MxJwkfpx90cqG9AFL0JAYo)
  - Support flattening dependencies (Inspired by [glide](https://github.com/Masterminds/glide))
  - Support renaming local packages
  - Support private repository
  - `build`/`test` dependencies are managed separately
  - Support dependency tree visualization
- Support build/test/single-test/wildcard-test/cross-compile
- Modern production-grade support for automatic build, simple to define customized tasks
- Native syntax of gradle
- Additional features for users in mainland China who are behind the [GFW](https://en.wikipedia.org/wiki/Great_Firewall)
- Support shadowsocks proxy 
- IDE support (IntelliJIDEA/Gogland/Webstorm/PhpStorm/PyCharm/RubyMine/CLion/Vim)
- Support incremental build (developing)

## Highlight

- Project-scope build
- Perfect cross-platform support
- Almost all external package management supported
- Fully test coverage
- Long-term support
- Various gradle plugin to enpower your build


## Table of Content

- [Getting Started](./docs/getting-started.md)
- [Dependency Management](./docs/dependency-management.md)
- [Tasks in Gogradle](./docs/tasks.md)
- [Repository Management](./docs/repository-management.md)
- [Set Proxy For Build](./docs/proxy.md)
- [Build Output and Cross Compile](./docs/cross-compile.md)
- [IDE Integration](./docs/ide.md)

> **NOTE** Gogradle doesn't conform Golang's global [workspace convention](https://golang.org/doc/code.html#Workspaces). It will generate a project-scoped GOPATH for each project instead of use the global one.


## Contributing

If you like Gogradle, star it please.

Please feel free to submit an [issue](https://github.com/blindpirate/gogradle/issues/new).

Fork and [PR](https://github.com/blindpirate/gogradle/pulls) are always welcomed.
