TODO:
- Update this file
- Update docs/manual.md
- Update index.rst
- Update docs/installation.md
- Make sure all links are correct

#  WipeReads
This tool is part of BIOPET tool suite that is developed at LUMC by [the SASC team](http://sasc.lumc.nl/). 
Each tool in the BIOPET tool suite is meant to offer a standalone function that can be used to perform a
dedicate data analysis task or added as part of [BIOPET pipelines](http://biopet-docs.readthedocs.io/en/latest/).

#  About this tool
WipeReads is a tool for removing reads from indexed BAM files that are inside a user defined region. It takes pairing
information into account and can be set to remove reads if one of the pairs maps outside of the target region. An
application example is to remove reads mapping to known ribosomal RNA regions (using a supplied BED file containing
intervals for these regions).

#  Documentation
For documentation and manuals visit the [readthedocs page](http://biopet-wipereads.readthedocs.io/en/latest/).


#  Contact

<p>
  <!-- Obscure e-mail address for spammers -->
For any question related to this tool, please use the github issue tracker or contact 
  <a href='http://sasc.lumc.nl/'>the SASC team</a> directly at: <a href='&#109;&#97;&#105;&#108;&#116;&#111;&#58;
 &#115;&#97;&#115;&#99;&#64;&#108;&#117;&#109;&#99;&#46;&#110;&#108;'>
  &#115;&#97;&#115;&#99;&#64;&#108;&#117;&#109;&#99;&#46;&#110;&#108;</a>.
</p>
